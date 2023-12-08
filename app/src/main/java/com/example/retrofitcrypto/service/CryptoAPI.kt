package com.example.retrofitcrypto.service

import com.example.retrofitcrypto.model.CryptoModel
import retrofit2.http.GET


interface CryptoAPI {

    //Get , post ,update, delete
    //https://raw.githubusercontent.com
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): retrofit2.Call<List<CryptoModel>>




}