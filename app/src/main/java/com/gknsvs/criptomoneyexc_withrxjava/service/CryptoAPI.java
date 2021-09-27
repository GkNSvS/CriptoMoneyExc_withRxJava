package com.gknsvs.criptomoneyexc_withrxjava.service;

import com.gknsvs.criptomoneyexc_withrxjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //https://api.nomics.com/v1/prices?key=50b857ed0770ac4dfa3f90736d06fd94616485c2
    @GET("prices?key=50b857ed0770ac4dfa3f90736d06fd94616485c2")
    Observable<List<CryptoModel>> getData();
}
