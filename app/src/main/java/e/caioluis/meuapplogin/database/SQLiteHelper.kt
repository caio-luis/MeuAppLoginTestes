package e.caioluis.meuapplogin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception
import java.lang.StringBuilder

class SQLiteHelper(

    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int

) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {

        try {
            var sb = StringBuilder()

            sb.append(
                "CREATE TABLE IF NOT EXISTS [itens] (\n" +
                        "  [iditem] INT NOT NULL, \n" +
                        "  [conteudo] TEXT NOT NULL, \n" +
                        "  CONSTRAINT [] PRIMARY KEY ([iditem]));"
            )

            val comandos = sb.toString().split(";")

            for (i in 0 until comandos.size) {
                db?.execSQL(comandos[i].toLowerCase())
            }

        } catch (ex: Exception) {

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            var sb = StringBuilder()

            sb.append("DROP TABLE IF EXISTS [itens];")

            val comandos = sb.toString().split(";")

            for (i in 0 until comandos.size) {
                db?.execSQL(comandos[i].toLowerCase())
            }

        } catch (ex: Exception) {

        }
        onCreate(db)
    }
}