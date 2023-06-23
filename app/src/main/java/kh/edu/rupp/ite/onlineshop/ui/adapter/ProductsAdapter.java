package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import kh.edu.rupp.ite.onlineshop.api.model.Products;
import kh.edu.rupp.ite.onlineshop.databinding.ViewHolderProductsBinding;


import com.squareup.picasso.Picasso;


public class ProductsAdapter extends ListAdapter<Products,ProductsAdapter.ViewHolder> {
    public ProductsAdapter() {

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
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProductsBinding binding = ViewHolderProductsBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        Products item = getItem(position);
        holder.bind(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ViewHolderProductsBinding itemBinding;

        public ViewHolder(ViewHolderProductsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Products products){
            Picasso.get().load(products.getImageurl()).into(itemBinding.imgProducts);
            itemBinding.nametxt.setText(products.getName());
            itemBinding.pricetxt.setText( "$" + products.getPrice());
        }
    }
}