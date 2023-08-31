package com.example.despensa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.despensa.HomeActivity;
import com.example.despensa.R;
import com.example.despensa.adapters.DisposalCardAdapter;
import com.example.despensa.callbacks.SwipeToDeleteCallback;
import com.example.despensa.decorations.VerticalSpaceItemDecoration;
import com.example.despensa.managers.TipsManager;
import com.example.despensa.managers.UserManager;
import com.example.despensa.objects.RecycleTip;

import java.util.ArrayList;
import java.util.List;

public class RecycleFragment extends Fragment {
    TipsManager tipsManager;
    private RecyclerView recyclerView;
    private DisposalCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Adicione um espaçamento entre os items da RecyclerView
        int spacing = getResources().getDimensionPixelSize(R.dimen.spacing_between_cards);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spacing)); // Classe definida abaixo

        tipsManager = UserManager.getInstance().getTipsManager();

        List<RecycleTip> recycleTipList = tipsManager.getNewTips();

        adapter = new DisposalCardAdapter(recycleTipList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // viu as ultimas dicas
        tipsManager.setNewTipsCount(0);
        updateBadge();

        return view;
    }

    /*private List<RecycleTip> generateDummyData() {
        List<RecycleTip> dummyList = new ArrayList<>();
        // Adicione mais itens de exemplo conforme necessário
        return dummyList;
    }*/

    private void updateBadge() {
        int newTipsCount = tipsManager.getNewTipsCount();
        ((HomeActivity) requireActivity()).updateBadgeCount(newTipsCount);
    }
}
