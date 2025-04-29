package com.abc.retrofitfullcourse.newRetrofitforpayments

data class CardDetails(
    val order_id: String,
    val auto_clearing: Int = 1,
    val amount: Float,
    val currency: String = "USD", // или "USD"
    val description: String,
    val test: Int = 0,
    val options: Options,
    val transaction: Transaction
)

data class Options(
    val custom_params: Map<String, String> = emptyMap(),
    val user: User
)

data class User(
    val email: String,
    val phone: String
)

data class Transaction(
    val type: String = "tokenized_card",
    val options: TransactionOptions
)

data class TransactionOptions(
    val token: String,
    val card_cvv: Int
)
