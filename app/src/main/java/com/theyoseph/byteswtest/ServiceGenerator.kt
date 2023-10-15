package com.theyoseph.byteswtest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val retroFit = Retrofit.Builder()
        .baseUrl("https://mocki.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(service: Class<T>): T{
        return retroFit.create(service)
    }
}