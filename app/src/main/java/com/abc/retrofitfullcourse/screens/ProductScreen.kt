package com.abc.retrofitfullcourse.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.abc.retrofitfullcourse.Retrofir.Product
import com.abc.retrofitfullcourse.Retrofir.ViewModelRetrofit
import com.abc.retrofitfullcourse.newRetrofitforpayments.ViewModelforFreedomPay
import com.abc.retrofitfullcourse.ui.theme.colorbuy
import com.abc.retrofitfullcourse.ui.theme.starscolor


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun ProductScreen(navController: NavController, listviewmodel: BasketViewmodel) {
    val viewmodel = hiltViewModel<ViewModelRetrofit>()

    LaunchedEffect (Unit){
        viewmodel.getAllProduct()
    }





    Scaffold(modifier = Modifier.fillMaxSize() ,
        topBar = {
        TopAppBar(title = {
            Text(text = "Market")
        } ,
            actions = {
                Icon(Icons.Default.ShoppingCart , ""  , modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp)
                    .clickable {
                        navController.navigate("basketpage")
                })
            })
    }) {paddingvalues->

        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingvalues)) {
            itemsIndexed(viewmodel.Allproduct) { index, item ->
                CardforEachProduct(item  , OnClickBuy = {
                    listviewmodel.listofBuys.add(item)
                })
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }




}





@Composable
fun CardforEachProduct(item: Product , OnClickBuy:()->Unit) {
    val viewmodel =  hiltViewModel<ViewModelforFreedomPay>()

    Card (
        modifier =Modifier.fillMaxWidth().wrapContentHeight() ,
        ){
        Row{

            LazyRow(modifier = Modifier.weight(1f)) {
                itemsIndexed(item.images){
                    index, item ->
                    AsyncImage(
                        model = item ,
                        "" ,
                        modifier = Modifier.size(150.dp)

                    )
                }
            }
           Column(modifier = Modifier.weight(3f)) {

               Text(text = item.title , fontSize = 25.sp , fontWeight = FontWeight.W900 )
//               Text(text = item.description , fontWeight = FontWeight.W500)
               Row(modifier = Modifier.fillMaxWidth()){
                   Text(text = item.brand  , fontWeight = FontWeight.W200)
                   VerticalDivider(
                       thickness =5.dp,
                       color = Color.Gray
                   )
                   Text(text = "#${item.category}" , fontWeight = FontWeight.W200)
               }

               Row(modifier = Modifier.fillMaxWidth() , ) {
//                   Text(text = item.availabilityStatus)

                   Text(text = item.returnPolicy , fontWeight =FontWeight.W200   )
                    Spacer(modifier = Modifier.padding(5.dp))
                   Text(text = item.warrantyInformation , fontWeight = FontWeight.W200)
               }

               Row(modifier = Modifier.fillMaxWidth()  , horizontalArrangement = Arrangement.SpaceAround){
                   Row{
                       Box(modifier = Modifier.padding(start =4.dp).clip(CircleShape).size(50.dp)
                           .background(starscolor) , contentAlignment = Alignment.Center){
                           Text(text = "${item.rating}" , fontWeight = FontWeight.W500 )
                       }
                       Box(modifier = Modifier
                           .wrapContentWidth()
                           .height(50.dp)
                           , contentAlignment = Alignment.Center){
                           Row(modifier = Modifier.wrapContentWidth().clip(CircleShape).background(Color.DarkGray)){
                               for(i in 1 .. 5 ){
                                   var color = starscolor
                                   if(item.rating >= i){
                                       Icon(Icons.Default.Star , "" , tint = starscolor)

                                   }else if(item.rating > i - 1 && item.rating < i){
                                       Box(
                                           modifier = Modifier
                                               .size(24.dp)
                                               .background(
                                                   brush = Brush.horizontalGradient(
                                                       listOf(starscolor, Color.Gray),
                                                       startX = 0f,
                                                       endX = 100f
                                                   ),
                                                   shape = CircleShape // или RectangleShape
                                               )
                                       ) {
                                           Icon(
                                               Icons.Default.Star
                                               ,
                                               contentDescription = null,
                                               tint = Color.Unspecified, // Важно! Чтобы фон был виден
                                               modifier = Modifier.fillMaxSize()
                                           )
                                       }

                                   }else{
                                       Icon(Icons.Default.Star , "" , tint = Color.Gray)
                                   }


                               }
                           }
                       }

                   }


                   Text(text = "")

               }







               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(vertical = 8.dp),
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   val afterDiscount = 100 - item.discountPercentage
                   val realCost = if (afterDiscount > 0) (item.price / afterDiscount) * 100 else item.price.toDouble()
                   val formattedRealCost = String.format("%.2f", realCost)

                   // Старая цена, зачёркнутая
                   Text(
                       text = "$formattedRealCost $",
                       fontSize = 18.sp,
                       fontWeight = FontWeight.Normal,
                       color = Color.Gray,
                       textDecoration = TextDecoration.LineThrough
                   )

                   // Скидка в ярком бейджике
                   Box(
                       modifier = Modifier
                           .padding(horizontal = 8.dp)
                           .background(Color.Red, shape = RoundedCornerShape(6.dp))
                           .padding(horizontal = 6.dp, vertical = 2.dp)
                   ) {
                       Text(
                           text = "-${item.discountPercentage}%",
                           fontSize = 14.sp,
                           fontWeight = FontWeight.Bold,
                           color = Color.White
                       )
                   }

                   // Текущая цена — акцент!
                   Text(
                       text = "${item.price} $",
                       fontSize = 24.sp,
                       fontWeight = FontWeight.Bold,
                       color = Color(0xFF2E7D32) // тёмно-зелёный, как "скидка"
                   )
               }

               Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween){
                   Text(text = item.shippingInformation , modifier = Modifier.weight(2f))

                   Button(modifier = Modifier.weight(2f) .fillMaxWidth() ,
                       onClick = {
                           OnClickBuy()
                   } , colors = ButtonDefaults.buttonColors(colorbuy)) {
                       Box(modifier = Modifier.background(colorbuy)){
                           Text(text = "Buy" , fontSize = 26.sp , fontWeight = FontWeight.W500 , color = Color.White)
                       }
                   }

               }

           }
        }
    }
}


