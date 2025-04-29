package com.abc.retrofitfullcourse.newRetrofitforpayments

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIPayments {

    @Headers("Content-Type: application/json")
    @POST("v5/sdk/tokenize")
    suspend fun tokenizeCard(
        @Header("Request-Id") requestId: String,
        @Body cardDetails: ToMe
    ): ResponceFromSign

    @Headers("Content-Type: application/json")
    @POST("v5/sdk/charge")
    suspend fun payWithCard(
        @Header("Request-Id") requestId: String,
        @Body cardDetails: CardDetails
    ): ResponsefromCard
}
