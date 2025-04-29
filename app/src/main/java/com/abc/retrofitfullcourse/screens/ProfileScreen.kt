package com.abc.retrofitfullcourse.screens

import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.abc.retrofitfullcourse.Retrofir.ViewModelRetrofit


@Composable
fun ProfileScreen(navcontroller: NavHostController , viewmodel:ViewModelRetrofit) {



    val userinfo  = viewmodel.userinfo





    Column(modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
        Box{
            AsyncImage(
                model = userinfo.image , "" ,
                modifier = Modifier.size(120.dp)
                    .clip(CircleShape)
            )
        }
        Text(
            text = "${userinfo.firstName} ${userinfo.lastName}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "@"+ "${userinfo.username}" , fontSize =  14.sp ,
            fontWeight = FontWeight.W200)

        Text(text = userinfo.gender , fontSize =  14.sp ,
            fontWeight = FontWeight.W200)
        Text(
            text = "📧 ${userinfo.email}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = androidx.compose.ui.graphics.Color.Gray
        )










    }
}