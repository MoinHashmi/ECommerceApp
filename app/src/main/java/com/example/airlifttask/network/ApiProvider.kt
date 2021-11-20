package com.example.airlifttask.network

import com.example.airlifttask.home.model.Product
import com.example.airlifttask.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


interface ApiProvider {

    companion object{
        private var apiProvider:ApiProvider?=null

        fun getInstance():ApiProvider{
            if (apiProvider==null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().apply {
                    readTimeout(30, TimeUnit.SECONDS)
                    writeTimeout(30, TimeUnit.SECONDS)
                    connectTimeout(30, TimeUnit.SECONDS)
                    addInterceptor(interceptor)
                    addInterceptor { chain ->
                        var request = chain.request()
                        request = request.newBuilder()
                            .build()
                        val response = chain.proceed(request)
                        response
                    }
                }


                    val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()


                apiProvider = retrofit.create(ApiProvider::class.java)
            }
            return apiProvider!!
        }

    }

    @GET("products")
    suspend fun getProductList(): Response<ArrayList<Product>>

}