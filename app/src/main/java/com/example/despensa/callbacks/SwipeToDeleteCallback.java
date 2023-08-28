package com.example.despensa.callbacks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.despensa.adapters.DisposalCardAdapter;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private DisposalCardAdapter adapter;

    public SwipeToDeleteCallback(DisposalCardAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // Não estamos tratando de movimento, portanto, retornamos false
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // Remove o item ao deslizar
        int position = viewHolder.getAdapterPosition();
        adapter.removeItem(position);
    }
}
