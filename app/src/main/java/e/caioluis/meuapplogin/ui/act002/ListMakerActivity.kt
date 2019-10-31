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
import e.caioluis.meuapplogin.adapter.ListAdapter
import e.caioluis.meuapplogin.model.HashMapItems
import e.caioluis.meuapplogin.ui.act003.Tests
import e.caioluis.meuapplogin.util.cleanEditText
import e.caioluis.meuapplogin.util.showMessage
import e.caioluis.meuapplogin.util.validateEditText
import kotlinx.android.synthetic.main.activity_list_maker.*

class ListMakerActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var listAdapter: ListAdapter

    private var myList: ArrayList<HashMapItems> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_maker)

        initVars()
        initActions()
    }

    private fun initVars() {

        context = this@ListMakerActivity

        listAdapter = ListAdapter(
            context,
            R.layout.list_item_cell,
            myList
        )
        list_maker_lv.adapter = listAdapter
    }

    private fun initActions() {

        list_maker_insert_btn.setOnClickListener {

            if (!validateEditText(list_maker_item_et)) {

                showMessage(context, R.string.error_message_empty_field)

                return@setOnClickListener
            }

            addToList(myList)
            cleanEditText(list_maker_item_et)
        }

        list_maker_delete_btn.setOnClickListener {

            listAdapter.deleteSelectedItems()

            list_maker_delete_btn.isVisible = false
        }

        list_maker_lv.setOnItemClickListener { parent, view, position, id ->

            listAdapter.doSelection(position)

            list_maker_delete_btn.isVisible =
                listAdapter.selectedItemsNumber() > 0
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val mIntent = Intent(context, Tests::class.java)

        startActivityForResult(mIntent, INTENTPARAMETER)

        return true
    }

    private fun addToList(list: ArrayList<HashMapItems>) {

        val item = list_maker_item_et.text.toString()

        val hmAux = HashMapItems()

        hmAux[HashMapItems.ITEMCONTENT] = item
        hmAux[HashMapItems.ITEM_ID] = ((list.size) + 1).toString()

        list.add(hmAux)

        listAdapter.notifyDataSetChanged()
    }

    private fun addToList(list: ArrayList<HashMapItems>, content: String) {

        val hmHelper = HashMapItems()

        hmHelper[HashMapItems.ITEMCONTENT] = content
        hmHelper[HashMapItems.ITEM_ID] = ((list.size) + 1).toString()

        list.add(hmHelper)

        listAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        processResult(resultCode, data)
    }

    private fun processResult(resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {

            val rContent = data?.getStringExtra(HashMapItems.ITEMCONTENT)

            addToList(myList, rContent!!)

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

