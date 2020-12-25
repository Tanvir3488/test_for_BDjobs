package com.tanvir.mobile_price_list.Network

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object {
        val base_url= "http://corporate3.bdjobs.com"



        val client =OkHttpClient.Builder().apply {

           /* val cacheSize = 10 * 1024 * 1024

            val cache = Cache(getgetCacheDir(), cacheSize.toLong())*/

            this.connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(20, TimeUnit.MINUTES)
                .writeTimeout(25, TimeUnit.MINUTES)
                .interceptors().add(Interceptor {

                    val request = it.request()
                    var response = it.proceed(request)
                    var tryCount = 0
                    while (!response.isSuccessful && tryCount < 3) {
                        Log.d("intercept", "Request is not successful - $tryCount")
                        tryCount++

                        // retry the request
                        response = it.proceed(request)
                    }

                    return@Interceptor response

                }

                )

                    }.build()



        fun getRetrofitInstance ():Retrofit{
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl(base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }


    }
}