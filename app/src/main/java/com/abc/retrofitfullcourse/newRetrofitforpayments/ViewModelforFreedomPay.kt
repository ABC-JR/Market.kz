package com.abc.retrofitfullcourse.newRetrofitforpayments

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.abc.retrofitfullcourse.DbCardInformation.DbCard
import com.abc.retrofitfullcourse.screens.Username

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ViewModelforFreedomPay @Inject constructor() :ViewModel() {

    val retrofit :Retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.freedompay.kz/").addConverterFactory(GsonConverterFactory.create()).build()

    val api  = retrofit.create(APIPayments ::class.java)


    val requestid =""

    var savecvv:String =  ""
    var email:String =  ""
    var numberphone:String =  ""



    fun registrationCard(textname: String, digit16: String, mm: String, yy: String, cvv: String , context: Context) {
        val database = DbCard(context = context, null)
        CoroutineScope(Dispatchers.IO).launch {
            val res  = api.tokenizeCard(requestid , ToMe(type = "FORTEBANK" , Optionss(digit16 ,textname , mm , yy )))
            Log.e("Mytagfirst"  , "${res.status}")
            Log.e("Mytagfirst"  , "${res.data}")
            savecvv = cvv
            database.insertcard(
                name = textname, digit16, mm, yy,
                cvv = cvv,
                email = email,
                number_phone = numberphone,
                username = Username.username,
            )
        }
    }
    fun payforsmth(amount: Float){
        CoroutineScope(Dispatchers.IO).launch{
            val res = api.payWithCard(requestid , CardDetails(
                order_id = "",
                amount = amount,
                description = "",
                options = Options(
                    user = User(
                        email = email ,
                        phone = numberphone
                    )
                ),
                transaction = Transaction(

                    options = TransactionOptions(
                        token = requestid,
                        card_cvv = savecvv.toInt(),
                    )
                )
            ))


            Log.e("Mytag" , "${res.status}"   )
            Log.e("Mytag" , "${res.dataFromPaying}"   )
        }

    }


}