package com.example.retrofitcrypto.model
import com.google.gson.annotations.SerializedName

data class CryptoModel(

    @SerializedName("currency")//json dan gelecek olan data değişken ismiyle aynı olmak zorunda
    //eğer değişken ismi zaten aynıysa serialized a gerek yok
    val currency:String,
    @SerializedName("price")//json dan gelecek olan data değişken ismiyle aynı olmak zorunda
    //eğer değişken ismi zaten aynıysa serialized a gerek yok
    val price:String

)