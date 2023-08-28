package com.example.despensa.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.despensa.HomeActivity;
import com.example.despensa.ProductRegistrationActivity;
import com.example.despensa.R;
import com.example.despensa.adapters.ProductListAdapter;
import com.example.despensa.managers.UserManager;
import com.example.despensa.objects.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final int REQUEST_CODE_PRODUCT_REGISTRATION = 1;
    private ProductListAdapter adapter;
    private ListView productsListView;
    private List<Product> userProductsList;
    private FloatingActionButton addProductButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        productsListView = rootView.findViewById(R.id.productsListView);
        addProductButton = rootView.findViewById(R.id.addProductButton);

        userProductsList = UserManager.getInstance().getLogedUser().getProductsList();
        Product product;
        product = new Product("Banana", 1, "", "", "",R.drawable.ic_product_banana);

        userProductsList.add(product);

        adapter = new ProductListAdapter(getActivity(), R.layout.list_item_product, userProductsList);
        productsListView.setAdapter(adapter);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a atividade de cadastro de novo produto
                Intent intent = new Intent(getActivity(), ProductRegistrationActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PRODUCT_REGISTRATION && resultCode == RESULT_OK) {
            if (data != null) {
                String newProduct = data.getStringExtra("newProduct");
                String purchaseDate = data.getStringExtra("purchaseDate");
                String expirationDate = data.getStringExtra("expirationDate");
                Integer quantity = Integer.parseInt(data.getStringExtra("quantity"));
                String category = data.getStringExtra("category");
                if (newProduct != null && purchaseDate != null && expirationDate != null && quantity != null && category != null) {
                    UserManager.getInstance().getLogedUser().getProductsList().add(new Product(newProduct, quantity, category, purchaseDate, expirationDate, R.drawable.ic_product_placeholder));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
    // Método para converter a string da data para LocalDate
    private Date convertToDate(String dateStr) {
        Date data = null;
        try {
            // Converta a string da data para um objeto Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            data = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
}