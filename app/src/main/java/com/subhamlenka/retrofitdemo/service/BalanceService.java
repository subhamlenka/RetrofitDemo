package com.subhamlenka.retrofitdemo.service;

import com.subhamlenka.retrofitdemo.model.BalanceData;
import com.subhamlenka.retrofitdemo.model.BalanceResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface BalanceService {

    @POST("api/wallet/1/")
    Call<BalanceResponse> updateBalance(@Body BalanceData balanceData);
}

