package com.theyoseph.byteswtest

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v1/eeced007-6b29-4c9d-ab63-c115a990d927")
    fun getData(): Call<MutableList<ResponseAPIElement>>
}