package com.gknsvs.criptomoneyexc_withrxjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.gknsvs.criptomoneyexc_withrxjava.R;
import com.gknsvs.criptomoneyexc_withrxjava.adapter.RecyclerViewAdapter;
import com.gknsvs.criptomoneyexc_withrxjava.databinding.ActivityMainBinding;
import com.gknsvs.criptomoneyexc_withrxjava.model.CryptoModel;
import com.gknsvs.criptomoneyexc_withrxjava.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL="https://api.nomics.com/v1/";
    Retrofit retrofit;

    RecyclerViewAdapter recyclerViewAdapter;
    CompositeDisposable compositeDisposable;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        //https://api.nomics.com/v1/prices?key=50b857ed0770ac4dfa3f90736d06fd94616485c2


        //Retrofit and Json

        Gson gson=new GsonBuilder().setLenient().create();

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        loadData();
    }

    private void loadData() {
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);
        compositeDisposable= new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse));

    }

    private void handleResponse(List<CryptoModel> _cryptoModels) {
        cryptoModels=new ArrayList<>(_cryptoModels);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
        binding.recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}