package com.abc.retrofitfullcourse.DbCardInformation

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.abc.retrofitfullcourse.newRetrofitforpayments.Optionss

class DbCard(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app3", factory, 3) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE CARD(name TEXT, " +
                "number TEXT, " +
                "mm TEXT, " +
                "yy TEXT," +
                " cvv TEXT , " +
                "email TEXT ,number_phone TEXT , username TEXT  )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS CARD"
        db?.execSQL(query)
        onCreate(db)
    }

    fun insertcard(name: String, number: String, mm: String, yy: String, cvv: String  ,email :String ,
                   number_phone :String , username:String
                   ) {
        val db = writableDatabase
        val query = "INSERT INTO CARD VALUES (?, ?, ?, ?, ? ,? ,? , ?)"
        val statement = db.compileStatement(query)
        statement.bindString(1, name)
        statement.bindString(2, number)
        statement.bindString(3, mm)
        statement.bindString(4, yy)
        statement.bindString(5, cvv)
        statement.bindString(6, email)
        statement.bindString(7, number_phone)
        statement.bindString(8, username)
        statement.execute()
    }

    fun delete(name: String, number: String, mm: String, yy: String, cvv: String) {
        val db = writableDatabase
        val query = "DELETE FROM CARD WHERE name =? AND number =? AND mm =? AND yy =? AND cvv =?"
        val values = arrayOf(name, number, mm, yy, cvv)
        db.execSQL(query, values)
    }

    @SuppressLint("Range")
    fun gettal(username :String): List<AllDataCard> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM CARD WHERE username = ?", arrayOf(username))
        val cardList = mutableListOf<AllDataCard>()

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val number = cursor.getString(cursor.getColumnIndex("number"))
                val mm = cursor.getString(cursor.getColumnIndex("mm"))
                val yy = cursor.getString(cursor.getColumnIndex("yy"))
                val cvv = cursor.getString(cursor.getColumnIndex("cvv"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val phone_number = cursor.getString(cursor.getColumnIndex("number_phone"))
                cardList.add(AllDataCard( number,name ,  mm, yy , cvv ,email ,phone_number ))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return cardList
    }






}

data class AllDataCard(
val card_number:String ,
val card_holder_name:String ,
val card_exp_month:String ,
val  card_exp_year: String ,
val cvv :String ,
    val email: String ,
    val phone_number :String ,

)