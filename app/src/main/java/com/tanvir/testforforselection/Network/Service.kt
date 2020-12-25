package com.tanvir.mobile_price_list.Network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {
    @Headers( "Content-Type: application/json; charset=utf-8")
    @GET("/interviewtest/InterviewJson.json")
    suspend fun getAvilableJobsnfo():Response<JsonObject>



}