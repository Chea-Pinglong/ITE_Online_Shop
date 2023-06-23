package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.Products;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProductsBinding;
import kh.edu.rupp.ite.onlineshop.ui.adapter.ProductsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
        loadProductsListFromServer();
    }

    private void loadProductsListFromServer(){

        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        create service obj
        ApiService apiService = httpClient.create(ApiService.class);

        Call<List<Products>> task = apiService.loadProductsList();

        task.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(response.isSuccessful()){
                    showProductsList(response.body());
                }else {
                    Toast.makeText(getContext(),"Load Product Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Products>> call, Throwable t) {
                Toast.makeText(getContext(),"Load Product Fail",Toast.LENGTH_LONG).show();
                Log.e("ProductsFragment","Load Product fail: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    private void showProductsList(List<Products> productsList){

//        create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);

//        create adapter
        ProductsAdapter adapter = new ProductsAdapter();
        adapter.submitList(productsList);
        binding.recyclerView.setAdapter(adapter);

    }
}
