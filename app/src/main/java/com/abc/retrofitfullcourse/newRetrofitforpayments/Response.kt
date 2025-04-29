package com.abc.retrofitfullcourse.newRetrofitforpayments





data class ResponsefromCard(
    val status :String ,
    val dataFromPaying: DataFromPaying,


)

data class DataFromPaying(
    val thirdds :SomeDataFromPay ,
    val payment_id:String ,
    val payment_status:String
)

data class SomeDataFromPay(
    val acsurl:String ,
    val frame_url:String ,
    val md:String ,
    val pareq:String ,
    val version: String
)