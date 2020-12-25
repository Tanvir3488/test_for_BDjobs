package com.tanvir.testforforselection.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.JsonObject
import com.tanvir.mobile_price_list.Network.RetrofitInstance
import com.tanvir.mobile_price_list.Network.Service
import retrofit2.Response
import java.lang.Exception

class MainActivityRepository  {

    private val retService = RetrofitInstance.getRetrofitInstance().create(Service::class.java)


    val responseLiveData: LiveData<Response<JsonObject>> = liveData{
        try {
            val response = retService.getAvilableJobsnfo()
            emit(response)
        }catch ( e: Exception){


        }


    }
}