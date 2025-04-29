package com.abc.retrofitfullcourse.Retrofir

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@HiltViewModel
class ViewModelRetrofit @Inject constructor() : ViewModel() {

    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api = retrofit.create(Api::class.java)

    var token: String = ""
    var userinfo by mutableStateOf(
        ResponceUser(
            id = 0,
            firstName = "",
            email = "",
            username = "",
            image = "",
            lastName = "",
            gender = "",
            accessToken = "",
            refreshToken = "",
        )
    )

    var Allproduct by mutableStateOf<List<Product>>(emptyList())



    fun logincheck(user: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res = api.logintopage(AuticationUser(user.trim(), password.trim()))
                userinfo = res
                token = res.accessToken
                Log.e("Tagtag" ,  "${userinfo}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getAllProduct(){
        CoroutineScope(Dispatchers.IO).launch{
            try {

                val res = api.getAllproduct()
                Allproduct = res.list ?: emptyList()

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }



}
