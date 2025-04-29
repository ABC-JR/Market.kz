package com.abc.retrofitfullcourse.Retrofir

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface Api {
    @POST("auth/login")
    suspend fun logintopage(@Body auticationUser: AuticationUser) :ResponceUser



    @GET("me")
    suspend fun getuserInfo(@Header("Authorization") token:String):User





    @GET("products")
    suspend fun getAllproduct():Products


}