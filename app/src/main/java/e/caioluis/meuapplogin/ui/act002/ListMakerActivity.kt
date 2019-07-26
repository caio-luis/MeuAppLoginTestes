package e.caioluis.meuapplogin.ui.act002

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import e.caioluis.meuapplogin.R
import e.caioluis.meuapplogin.adapter.MyListAdapter
import e.caioluis.meuapplogin.model.HashMapItems
import e.caioluis.meuapplogin.ui.act003.Tests
import e.caioluis.meuapplogin.util.eraseEditText
import e.caioluis.meuapplogin.util.showMessage
import e.caioluis.meuapplogin.util.validateEditText
import kotlinx.android.synthetic.main.activity_list_maker.*
import android.widget.SimpleAdapter as SimpleAdapter

class ListMakerActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var myListAdapter: MyListAdapter

    private var mylist: ArrayList<HashMapItems> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_maker)

        initVars()
        initActions()
    }

    private fun initVars() {

        context = this@ListMakerActivity

        myListAdapter = MyListAdapter(
            context,
            R.layout.list_item_cell,
            mylist
        )
    }

    private fun initActions() {

        list_maker_insert_btn.setOnClickListener {

            if (validateEditText(list_maker_item_et)) {

                addToList(mylist)
                showList()
                eraseEditText(list_maker_item_et)

            } else {
                showMessage(context, R.string.error_message_empty_field)
            }
        }

        list_maker_delete_btn.setOnClickListener {

            myListAdapter.deleteSelectedItems()

            list_maker_delete_btn.isVisible = false

        }

        list_maker_lv.setOnItemClickListener { parent, view, position, id ->

            myListAdapter.doSelection(position)

            list_maker_delete_btn.isVisible =
                myListAdapter.selectedItemsNumber() > 0
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val mItent = Intent(context, Tests::class.java)

        startActivityForResult(mItent, INTENTPARAMETER)

        return true
    }

    private fun showList() {

        list_maker_lv.adapter = myListAdapter
    }

    private fun addToList(minhalista: ArrayList<HashMapItems>): ArrayList<HashMapItems> {

        val item = list_maker_item_et.text.toString()
        val hmAuxiliar = HashMapItems()

        hmAuxiliar[HashMapItems.ITEMCONTENT] = item
        hmAuxiliar[HashMapItems.ITEM_ID] = ((minhalista.size) + 1).toString()

        minhalista.add(hmAuxiliar)

        return minhalista
    }

    private fun addToList(list: ArrayList<HashMapItems>, content: String): ArrayList<HashMapItems> {

        val hmHelper = HashMapItems()

        hmHelper[HashMapItems.ITEMCONTENT] = content
        hmHelper[HashMapItems.ITEM_ID] = ((list.size) + 1).toString()

        list.add(hmHelper)

        return list
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        processResult(resultCode, data)
    }

    private fun processResult(resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {

            val rContent = data?.getStringExtra(HashMapItems.ITEMCONTENT)

            addToList(mylist, rContent!!)
            showList()

        } else {
            showMessage(context, R.string.error_message_canceled)
        }
    }

    companion object {
        const val INTENTPARAMETER = 10
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }
}

