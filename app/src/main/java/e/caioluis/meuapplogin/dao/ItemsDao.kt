package e.caioluis.meuapplogin.dao

import android.content.Context
import e.caioluis.meuapplogin.database.Dao

class ItemsDao (context: Context) : Dao(context) {

    companion object {
        const val TABLE = "table"
        const val ID = "id"
        const val CONTENT = "content"
    }
}