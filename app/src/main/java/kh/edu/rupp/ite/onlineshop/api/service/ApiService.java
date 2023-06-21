package kh.edu.rupp.ite.onlineshop.api.service;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.Products;
import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

//    product
    @GET("/Midterm/Products/products.json")
    Call<List<Products>> loadProductsList();

//    profile
    @GET("/Midterm/Profile/profile.json")
    Call<List<Profile>> loadProfileList();
}
