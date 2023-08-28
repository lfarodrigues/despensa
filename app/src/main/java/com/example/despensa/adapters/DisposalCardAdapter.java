package com.example.despensa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.despensa.R;
import com.example.despensa.objects.RecycleTip;

import java.util.List;

public class DisposalCardAdapter extends RecyclerView.Adapter<DisposalCardAdapter.ViewHolder> {

    private List<RecycleTip> recycleTipList;
    private ItemTouchHelper itemTouchHelper; // ItemTouchHelper para lidar com o deslizar

    public DisposalCardAdapter(List<RecycleTip> recycleTipList) {
        this.recycleTipList = recycleTipList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_disposal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecycleTip info = recycleTipList.get(position);

        holder.textViewTitle.setText(info.getTitle());
        holder.textViewDescription.setText(info.getDescription());
    }

    @Override
    public int getItemCount() {
        return recycleTipList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }

    // Método para associar o ItemTouchHelper com a RecyclerView
    public void attachItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public void removeItem(int position) {
        recycleTipList.remove(position);
        notifyItemRemoved(position);
    }
}
