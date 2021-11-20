package com.example.airlifttask.network

import com.example.airlifttask.home.model.Product
import com.example.airlifttask.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiProvider {

    companion object{
        private var apiProvider:ApiProvider?=null

        fun getInstance():ApiProvider{
            if (apiProvider==null) {
                val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()


                apiProvider = retrofit.create(ApiProvider::class.java)
            }
            return apiProvider!!


        }

    }

    @GET("/users/list")
    suspend fun getProductList(): Response<ArrayList<Product>>

}