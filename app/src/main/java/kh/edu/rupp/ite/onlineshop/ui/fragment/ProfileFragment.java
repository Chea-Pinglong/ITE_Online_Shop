package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private TextView txtname, txtemail;
    private EditText edittxtemail, edittxtdob, edittxtphone, edittxtaddress, edittxtgender;

    private ImageView profileimg;
    public ProfileFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        txtname = binding.txtname;
        txtemail = binding.txtemail;
        edittxtaddress = binding.edittxtaddress;
        edittxtemail = binding.edittxtemail;
        edittxtdob = binding.edittxtdob;
        edittxtphone = binding.edittxtphone;
        edittxtgender = binding.edittxtgender;
        profileimg = binding.profileimg;

        loadProfileListFromServer();

        return  binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadProfileListFromServer();

    }

    private void loadProfileListFromServer(){

        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        create service obj
        ApiService apiService = httpClient.create(ApiService.class);
        Call<Profile> task = apiService.loadProfileList();

        task.enqueue(new Callback<Profile>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    Profile profile = response.body();
                    assert profile != null;
                    Picasso.get().load(profile.getImageUrl()).into(profileimg);

                    txtname.setText(profile.getFirst_name() + " " + profile.getLast_name());
                    txtemail.setText(profile.getEmail());

                    edittxtemail.setText(profile.getEmail());
                    edittxtaddress.setText(profile.getAddress());
                    edittxtdob.setText(profile.getBirthday());
                    edittxtgender.setText(profile.getGender());
                    edittxtphone.setText(profile.getPhoneNumber());
                }else {
                    Toast.makeText(getContext(),"Load Product Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(),"Load Profile Fail",Toast.LENGTH_LONG).show();
                Log.e("ProfileFragment","Load Profile fail: " + t.getMessage());
                t.printStackTrace();
            }
        });

    }

}

