package kh.edu.rupp.ite.onlineshop.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.ActivityLandingBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProductsFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProfileFragment;


public class LandingActivity extends AppCompatActivity {


    private ActivityLandingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.mnuHome) {
                showFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.mnuMore) {
                showFragment(new MoreFragment());
            } else if (item.getItemId() == R.id.mnuProduct) {
                showFragment(new ProductsFragment());
            } else if (item.getItemId() == R.id.mnuProfile) {
                showFragment(new ProfileFragment());
            }

            return true;

        });
    }

    private void showFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.layoutFragment, fragment);

        fragmentTransaction.commit();
    }

    private void showHomeFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();

        fragmentTransaction.replace(R.id.layoutFragment, homeFragment);

        fragmentTransaction.commit();
    }

    private void showMoreFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MoreFragment moreFragment = new MoreFragment();

        fragmentTransaction.replace(R.id.layoutFragment, moreFragment);

        fragmentTransaction.commit();
    }

    private void showProductsFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProductsFragment productsFragment = new ProductsFragment();

        fragmentTransaction.replace(R.id.layoutFragment, productsFragment);

        fragmentTransaction.commit();
    }

    private void showProfileFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProfileFragment profileFragment = new ProfileFragment();

        fragmentTransaction.replace(R.id.layoutFragment, profileFragment);

        fragmentTransaction.commit();
    }
}
