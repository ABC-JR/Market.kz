package com.abc.retrofitfullcourse

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abc.retrofitfullcourse.Retrofir.ViewModelRetrofit
import com.abc.retrofitfullcourse.screens.AddScreen
import com.abc.retrofitfullcourse.screens.AuthiticationPage
import com.abc.retrofitfullcourse.screens.BasketPage
import com.abc.retrofitfullcourse.screens.BasketViewmodel
import com.abc.retrofitfullcourse.screens.ProductScreen
import com.abc.retrofitfullcourse.screens.ProfileScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainPage() {
    val navcontroller = rememberNavController()
    val startdestination = "authpage"

    var canishowbottom by remember{ mutableStateOf(false)}
    Scaffold(modifier = Modifier.fillMaxSize()  ,
        bottomBar = {
            if(canishowbottom){
                MyBottomAppBar(navcontroller)
            }

        }
        ) {padding->

        val listviewmodel: BasketViewmodel = viewModel()
        val viewmodelretrofit = hiltViewModel<ViewModelRetrofit>()

        val navback =  navcontroller.currentBackStackEntryAsState()
        val currentroute = navback.value?.destination?.route


        if(!currentroute.equals("authpage")){
            canishowbottom = true
        }else{
            canishowbottom = false
        }
        NavHost(navController = navcontroller , startDestination = startdestination){
            composable("authpage") {
                Box(modifier = Modifier.padding(padding)){
                    AuthiticationPage(navcontroller , viewmodelretrofit)
                }
            }
            composable("profilepage") {
                Box(modifier = Modifier.padding(padding)){
                    ProfileScreen(navcontroller , viewmodelretrofit)
                }
            }
            composable("productpage"){
//                Box(modifier = Modifier.padding(padding)){


                ProductScreen(navcontroller , listviewmodel)
//                }
            }
            composable("addpage") {
                Box(modifier = Modifier.padding(padding)){
                    AddScreen(navcontroller)
                }
            }
            composable("basketpage") {
                Box(modifier = Modifier.padding(padding)){

                    BasketPage(listviewmodel)
                }
            }
        }
    }
}

@Composable
fun MyBottomAppBar(navController: NavController) {
    val list = listOf(
        triple(0 ,
            "ShopList" ,
                    "productpage" ,
            Icons.Default.List
            ) ,
        triple(1 ,
            "Add" ,
            "addpage" ,
            Icons.Default.Add)
        ,
        triple(2 ,
            "Profile",
            "profilepage",
            image = Icons.Default.AccountCircle,
        )
    )

    val selectedid by remember{
        mutableStateOf(0)
    }


    NavigationBar {
        list.forEach {   item->
            NavigationBarItem(
                selected = selectedid == item.id,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {Icon(item.image , "") } ,
                label = {
                    Text(text = item.name)
                }
            )
        }
    }
}


data class triple(
    val id :Int ,
    val name:String,
    val route:String,
    val image: ImageVector
)

