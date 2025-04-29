package com.abc.retrofitfullcourse.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.abc.retrofitfullcourse.Retrofir.Product
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class BasketViewmodel @Inject constructor() :ViewModel() {
    var listofBuys = mutableStateListOf<Product>()
}