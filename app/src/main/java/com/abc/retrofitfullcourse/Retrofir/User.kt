package com.abc.retrofitfullcourse.Retrofir

import com.google.gson.annotations.SerializedName

data class User (
    val id :Int,
    val firstName:String,
val lastname:String,
val name:String ,
val age:Int,
val gen:String,
val email:String ,
val phone:String,
val username: String,
val password:String ,
val birthDate:String,
val image:String ,
val bloodGroup: String,
val height:Double  ,
val weight:Double,
val university:String ,
val country:String ,
val role: String  ,
    val accesstoken:String ,
    val refreshtoken:String
)


data class ResponceUser(

    val id:Int ,
    val username:String ,
    val email:String ,
    val firstName:String,
    val lastName:String,
    val gender: String,
    val image: String,
    val accessToken: String ,
    val refreshToken:String

)


data class Product(
    val id:Int ,
    val title:String ,
    val description:String ,
    val category:String ,
    val price:Double ,
    val discountPercentage:Double ,
    val rating: Double ,
    val brand:String ,
    val weight: Double ,
    val warrantyInformation: String,
    val shippingInformation:String ,
    val availabilityStatus: String,
    val returnPolicy: String,
    val minimumOrderQuantity:Int ,
    val images:List<String>
) {
    constructor() : this(0 , "" , "" ,
        ""  ,0.0 , 0.0 ,0.0 ,"" ,
        0.0 , "" , "" , "" ,"" ,
        0 , listOf("" , "" , "")
    )
}


data class Products(
    @SerializedName("products")
    val list:List<Product>
)