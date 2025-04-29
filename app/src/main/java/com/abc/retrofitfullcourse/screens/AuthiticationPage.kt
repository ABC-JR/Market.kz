package com.abc.retrofitfullcourse.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abc.retrofitfullcourse.DbCardInformation.DbCard
import com.abc.retrofitfullcourse.Retrofir.ViewModelRetrofit



@Composable
fun AuthiticationPage(navcontroller: NavHostController , viewmodelretorrofit :ViewModelRetrofit) {

    
    val context = LocalContext.current
    val db = DbCard(context , null)


    viewmodelretorrofit.userinfo.username?.let {
        if (it.isNotEmpty()) {
            LaunchedEffect(Unit) {
                navcontroller.navigate("profilepage")
            }

            Username.username = viewmodelretorrofit.userinfo.username

        }
    }


    var isclicklogin = remember { mutableStateOf(false) }


    var login = remember{
        mutableStateOf("")
    }

    var pass =  remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        OutlinedTextField(
            value = login.value ,
            onValueChange = {
                login.value = it
            }  ,
            label = {
                Text(text ="Login")
            }
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = pass.value ,
            onValueChange = {
                pass.value = it
            }  ,
            label = {
                Text(text ="Password")
            } ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.padding(15.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .wrapContentHeight()
                .clip(CircleShape) ,
            onClick = {
                isclicklogin.value = true
            }) {
            Text("Get in")
        }


        if(isclicklogin.value){
            LaunchedEffect (Unit){
                viewmodelretorrofit.logincheck(login.value , pass.value)
            }
            isclicklogin.value = false
        }



    }
}