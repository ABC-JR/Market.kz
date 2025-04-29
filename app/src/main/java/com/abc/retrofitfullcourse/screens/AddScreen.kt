package com.abc.retrofitfullcourse.screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.abc.retrofitfullcourse.DbCardInformation.AllDataCard
import com.abc.retrofitfullcourse.DbCardInformation.DbCard
import com.abc.retrofitfullcourse.newRetrofitforpayments.ViewModelforFreedomPay
import com.abc.retrofitfullcourse.ui.theme.submitbutton


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddScreen(navcontroller: NavHostController) {
    val context = LocalContext.current
    val database = DbCard(context , null)
    val clickadd = remember { mutableStateOf(false) }
    val viewmodel = hiltViewModel<ViewModelforFreedomPay>()
    val list  by remember {
        mutableStateOf(database.gettal(Username.username))
    }

    if(clickadd.value){
        MyAlertDialog(onclick = {
            clickadd.value = false
        } )


    }

    var cardsize by remember {
        mutableStateOf(200.dp)
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick= {clickadd.value = true}) {
            Icon(Icons.Default.Add , "")
        }

    }) {

        LazyColumn {
            itemsIndexed(list){
                    index , item->
                    MyCardDetails(item , onclickCard = {
                        database.delete(item.card_holder_name , item.card_number , item.card_exp_month  , item.card_exp_year , item.cvv)
                    })
            }
        }
    }



}

@Composable
fun MyCardDetails(item: AllDataCard, onclickCard: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
                onclickCard()
            }
//            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = "https://www.nicoletbank.com/sites/default/files/2022-12/M032371%20Contactless%20-%20Consumer%20Credit%20-%20Blue.png",
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom, // <<< самое главное!!!
                horizontalAlignment = Alignment.Start
            ) {

                val numbercard = item.card_number ?: ""
                val realcardnumber = if (numbercard.length >= 16) {
                    numbercard.chunked(4).joinToString("  ")
                } else {
                    "Invalid Card Number"
                }

                // Номер карты
                Text(
                    text = realcardnumber,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(30.dp))


                Text(
                    text = "${item.card_exp_month} / ${item.card_exp_year}",
                    color = Color.White
                )

                Text(
                    text = item.card_holder_name,
                    color = Color.White
                )
            }
        }
    }
}





@Composable
fun MyAlertDialog(onclick:()->Unit ){
    Dialog(
//        icon = AsyncImage(""),
        onDismissRequest = {
            onclick()
        } ,

    ){
        val  viewmodel = hiltViewModel<ViewModelforFreedomPay>()

        var textname by remember{
            mutableStateOf("")
        }
        var digit16 by remember{
            mutableStateOf("")
        }
        var mm by remember{
            mutableStateOf("")
        }
        var yy by remember{
            mutableStateOf("")
        }
        var cvv by remember{
            mutableStateOf("")
        }
        var email by remember{
            mutableStateOf("")
        }
        var phone_number by remember{
            mutableStateOf("")
        }
        Card(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)) {

            AsyncImage(model = "https://xrprightnow.com/wp-content/uploads/2019/09/visa.png"  ,      "" ,)
            Column {
                OutlinedTextField(
                    value = textname ,
                    onValueChange = {
                        textname = it
                    } ,
                    label = {
                        Text(text = "Name Card")
                    } ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = digit16 ,
                    onValueChange = {
                        digit16 = it
                    } ,
                    label = {
                        Text(text = "Card Number")
                    } ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                )

                Row(modifier = Modifier.fillMaxWidth()){
                    OutlinedTextField(
                        value = mm ,
                        onValueChange = {
                            mm = it
                        } ,
                        label = {
                            Text(text = "Month")
                        } ,
                        modifier = Modifier.weight(2f) ,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    OutlinedTextField(
                        value = yy ,
                        onValueChange = {
                            yy  = it
                        } ,
                        label = {
                            Text(text = "Year")
                        } ,
                        modifier = Modifier.weight(2f) ,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                    )
                }

                OutlinedTextField(
                    value = cvv ,
                    onValueChange = {
                        cvv = it
                    } ,
                    label = {
                        Text(text = "CVV")
                    } ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                )


                OutlinedTextField(
                    value = email ,
                    onValueChange = {
                        email = it
                    } ,
                    label = {
                        Text(text = "email")
                    } ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = phone_number ,
                    onValueChange = {
                        phone_number = it
                    } ,
                    label = {
                        Text(text = "Phone Number")
                    } ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                )




                val context = LocalContext.current


                    Button(
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                        onClick = {
                            if(textname.isEmpty() || digit16.isEmpty() || mm.isEmpty() || yy.isEmpty() ||cvv.isEmpty()){
                                Toast.makeText(context , "Fields cannot be empty" , Toast.LENGTH_LONG).show()
                            }else{
                                if(mm.toInt() >= 0 || mm.toInt() <= 12){
                                    viewmodel.registrationCard(
                                        textname , digit16 , mm , yy , cvv , context
                                    )
                                }else{
                                    Toast.makeText(context , "Month only contains [0 - 12]" , Toast.LENGTH_LONG).show()
                                }

                            }


                        } , colors = ButtonDefaults.buttonColors(submitbutton)
                    ) {
                        Text(text = "Submit" , textAlign = TextAlign.Center)
                    }


            }



        }
    }
}