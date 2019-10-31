package e.caioluis.meuapplogin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import e.caioluis.meuapplogin.R
import e.caioluis.meuapplogin.model.HashMapItems

class ListAdapter(

    private val context: Context,
    private var resource: Int,
    private var list: ArrayList<HashMapItems>

) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    fun doSelection(position: Int) {

        val item = list[position]

        when {
            item[HashMapItems.STATUS].equals("1")
            -> item[HashMapItems.STATUS] = "0"
            else
            -> item[HashMapItems.STATUS] = "1"
        }

        notifyDataSetChanged()
    }

    fun selectedItemsNumber(): Int {
        var counter = 0

        for (i in 0 until list.size) {
            if (list[i][HashMapItems.STATUS].equals("1")) {
                counter++
            }
        }
        return counter
    }

    private fun selectedItemsPosition(): ArrayList<HashMapItems> {

        val selectedlist = ArrayList<HashMapItems>()

        for (i in 0 until list.size) {
            if (list[i][HashMapItems.STATUS].equals("1")) {
                selectedlist.add(list[i])
            }
        }
        return selectedlist
    }

    fun deleteSelectedItems() {

        val selecoes = selectedItemsPosition()

        list.removeAll(selecoes)

        notifyDataSetChanged()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val mView: View? = mInflater.inflate(resource, parent, false)

        val llBackground = mView?.findViewById<LinearLayout>(R.id.ll_item)
        val tvItem = mView?.findViewById<TextView>(R.id.item_tv_cell)

        val item = list[position]

        tvItem?.text = "${item[HashMapItems.ITEMCONTENT]}"

        if (item[HashMapItems.STATUS].equals("1")) {
            llBackground?.setBackgroundColor(context.getColor(R.color.selected_on))
        } else {
            llBackground?.setBackgroundColor(context.getColor(R.color.selected_off))
        }

        return mView!!
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position][HashMapItems.ITEM_ID]?.toLong() ?: 0L
    }

    override fun getCount(): Int {
        return list.size
    }
}