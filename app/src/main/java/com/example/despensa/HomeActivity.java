package com.example.despensa;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.despensa.adapters.ProductListAdapter;
import com.example.despensa.managers.UserManager;
import com.example.despensa.objects.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private final int REQUEST_CODE_PRODUCT_REGISTRATION = 1;
    private ProductListAdapter adapter;
    private ListView productsListView;
    private List<Product> userProductsList;

    private FloatingActionButton addProductButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productsListView = findViewById(R.id.productsListView);
        addProductButton = findViewById(R.id.addProductButton);

        userProductsList = UserManager.getInstance().getLogedUser().getProductsList();
        Product product;
        product = new Product("Banana", LocalDate.now(), LocalDate.now(), R.drawable.ic_product_banana);

        userProductsList.add(product);

        adapter = new ProductListAdapter(this, R.layout.list_item_product, userProductsList);
        productsListView.setAdapter(adapter);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a atividade de cadastro de novo produto
                Intent intent = new Intent(HomeActivity.this, ProductRegistrationActivity.class);
                startActivityForResult(intent, REQUEST_CODE_PRODUCT_REGISTRATION);
            }
        });

        /*productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Implemente o código para lidar com o clique em um produto na lista
            }
        });*/
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PRODUCT_REGISTRATION && resultCode == RESULT_OK) {
            if (data != null) {
                String newProduct = data.getStringExtra("newProduct");
                if (newProduct != null) {
                    userProductsList.add(new Product(newProduct, LocalDate.now(), LocalDate.now(), R.drawable.ic_product_placeholder));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

}