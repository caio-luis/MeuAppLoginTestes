package e.caioluis.meuapplogin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

open class Dao (
   private val context: Context
) {

    protected var db : SQLiteDatabase? = null

    fun openDataBase(){

        var helper = SQLiteHelper(
            context,
            Constants.DATABASE,
            null,
            Constants.VERSION
        )
        this.db = helper.writableDatabase
    }

    fun closeDataBase(){
        if (db != null)
            db?.close()
    }
}