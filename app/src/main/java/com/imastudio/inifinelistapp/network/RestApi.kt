package com.imastudio.inifinelistapp.network


import com.imastudio.inifinelistapp.model.ResponseListing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("/r/aww/hot.json")
    fun getDataListing(
        @Query("limit") loadSize: Int = 30,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
        ) : Call<ResponseListing>



}