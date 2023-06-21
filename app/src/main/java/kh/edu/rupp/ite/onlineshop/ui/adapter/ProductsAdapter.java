package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import kh.edu.rupp.ite.onlineshop.api.model.Products;

public class ProductsAdapter extends ListAdapter<Products,ProductsAdapter.ViewHolder> {
    protected ProductsAdapter() {
        super(new DiffUtil.ItemCallback<Products>() {
            @Override
            public boolean areItemsTheSame(@NonNull Products oldItem, @NonNull Products newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Products oldItem, @NonNull Products newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

    }
}
