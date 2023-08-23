package com.example.despensa.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.despensa.MainActivity;
import com.example.despensa.R;
import com.example.despensa.managers.UserManager;
import com.example.despensa.objects.Product;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {
    private Context context;
    private int resource;
    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Product product = getItem(position);

        if (product != null) {
            ImageView productImage = itemView.findViewById(R.id.productImage);
            TextView productName = itemView.findViewById(R.id.productName);

            productImage.setImageResource(product.getImageResourceId());
            productName.setText(product.getName());
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // popup de opções
                showOptionsPopup(product);
            }
        });

        return itemView;
    }

    private void showOptionsPopup(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Opções do Item")
                .setItems(new CharSequence[]{"Editar", "Deletar", "Consumir"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // Opção de editar selecionada
                                // Abra a tela de edição com os detalhes do item

                                break;
                            case 1:
                                // Opção de deletar selecionada
                                showDeleteConfirmationPopup(product);
                                break;
                            case 2:
                                // Opção de consumir selecionada
                                showConsumeDialog(product);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void showDeleteConfirmationPopup(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmar Exclusão")
                .setMessage("Deseja realmente excluir o item?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeProduct(product);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancelar a exclusão
                    }
                });
        builder.create().show();
    }

    private void removeProduct(Product product) {
        // Remova o item da lista de produtos do usuário
        // Você precisa atualizar a fonte de dados (ArrayList<Product>) e notificar o adaptador
        UserManager.getInstance().getLogedUser().getProductsList().remove(product);
        notifyDataSetChanged();
    }

    private void showConsumeDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Consumir Item");

        // Incluir um EditText no diálogo para inserir a quantidade
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("Consumir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Obter a quantidade inserida pelo usuário
                String quantityStr = input.getText().toString();
                int quantity = Integer.parseInt(quantityStr);

                // Atualizar a quantidade do produto
                consumeProduct(product, quantity);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cancelar o consumo
            }
        });

        builder.create().show();
    }

    private void consumeProduct(Product product, int quantity) {
        // Atualizar a quantidade do produto e remover se a quantidade for 0
        int newQuantity = product.getQuantity() - quantity;
        if (newQuantity > 0) {
            product.setQuantity(newQuantity);
            notifyDataSetChanged();
        } else {
            removeProduct(product);
        }

        // Exibir informações sobre como descartar corretamente o produto
        showDisposalInfo(product);
    }
    private void showDisposalInfo(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Descarte Correto")
                .setMessage("Para descartar corretamente o produto " + product.getName() +
                        ", siga as orientações fornecidas pela legislação local ou pelo fabricante/produtor. Na sua região," +
                        " a cor de lixeira comum para produtos da categoria " + product.getCategory() + " é essa:"
                )
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Fechar o diálogo
                    }
                });
        // Crie uma ImageView e defina a imagem usando o recurso de drawable
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(product.getRecycleImageId()); // Substitua pelo nome da sua imagem

        builder.setView(imageView);
        builder.setPositiveButton("Fechar", null);
        builder.create().show();
    }
}


