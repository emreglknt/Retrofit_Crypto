package com.example.retrofitcrypto.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcrypto.adapter.RecyclerAdapter
import com.example.retrofitcrypto.databinding.ActivityMainBinding
import com.example.retrofitcrypto.model.CryptoModel
import com.example.retrofitcrypto.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RecyclerAdapter.Listener{


    private val Base_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels:ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerAdapter?=null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        // RecyclerView

        val layoutManager :RecyclerView.LayoutManager=LinearLayoutManager(this)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = layoutManager


        loadData()


    }





    private fun loadData(){

        val retrofit = Retrofit.Builder().baseUrl(Base_URL)   // verilen urldeki verileri json formatına göre düzenleyip build ediyor
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {//eğerki response başarılı ve null gelmediyse
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter=RecyclerAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter=recyclerViewAdapter
                        }

                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
       Toast.makeText(this,"Clicked :${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }


}