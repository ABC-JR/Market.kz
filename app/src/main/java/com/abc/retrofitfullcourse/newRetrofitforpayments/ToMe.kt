package com.abc.retrofitfullcourse.newRetrofitforpayments

import com.google.gson.annotations.SerializedName

data class ToMe(
    val type:String = "bank_card" ,
    val optionss: Optionss = Optionss(
        "4405645000001234" ,
        "NAME" ,
        "02" ,
        "25"
    )
)

data class Optionss(
    val card_number:String ,
    val card_holder_name:String ,
    val card_exp_month:String ,
    val  card_exp_year: String
)




data class ResponceFromSign(
    val status :String ,
    val data : DataName
)


data class DataName(
    @SerializedName("data")
    val token:String
)