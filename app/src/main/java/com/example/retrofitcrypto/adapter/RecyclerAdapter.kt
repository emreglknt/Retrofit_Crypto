package com.example.retrofitcrypto.adapter

import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcrypto.databinding.RowLayoutBinding
import com.example.retrofitcrypto.model.CryptoModel




class RecyclerAdapter(private val cryptoList: ArrayList<CryptoModel>,private val listener:Listener) : RecyclerView.Adapter<RecyclerAdapter.RowHolder>()
 {


     interface Listener{
         fun onItemClick(cryptoModel: CryptoModel)
     }



    private var colors :Array<String> = arrayOf("#5b39c6","#9db847","#ba160c","#68a0b0","#6638e2","#b26334","#4fcca2","#cfcf0d")


     class RowHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cryptoModel: CryptoModel, colors: Array<String>, position: Int,listener:Listener) {
            binding.root.setBackgroundColor(android.graphics.Color.parseColor(colors[position % 8]))
            binding.textName.text = cryptoModel.currency
            binding.textPrice.text = cryptoModel.price
            binding.textName.setOnClickListener{
                listener.onItemClick(cryptoModel)
            }

        }


    }



     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
         val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return RowHolder(binding)
     }

     override fun onBindViewHolder(holder: RowHolder, position: Int) {
         holder.bind(cryptoList[position], colors, position,listener)
     }

     override fun getItemCount(): Int {
         return cryptoList.size
     }


}