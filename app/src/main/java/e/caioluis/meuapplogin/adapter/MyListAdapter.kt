package e.caioluis.meuapplogin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import e.caioluis.meuapplogin.R
import e.caioluis.meuapplogin.model.HashMapItems

class MyListAdapter(

    private val context: Context,
    private var resource: Int,
    private var data: ArrayList<HashMapItems>

) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    fun doSelection(position: Int) {

        val item = data[position]

        if (item[HashMapItems.STATUS].equals("1")) {
            item[HashMapItems.STATUS] = "0"

        } else {
            item[HashMapItems.STATUS] = "1"
        }
        notifyDataSetChanged()
    }

    fun selectedItemsNumber(): Int {
        var counter = 0

        for (i in 0 until data.size) {
            if (data[i][HashMapItems.STATUS].equals("1")) {
                counter++
            }
        }
        return counter
    }

    fun selectedItemsPosition(): ArrayList<HashMapItems> {

        var selectedlist = ArrayList<HashMapItems>()

        for (i in 0 until data.size) {
            if (data[i][HashMapItems.STATUS].equals("1")) {
                selectedlist.add(data[i])
            }
        }
        return selectedlist
    }

    fun deleteSelectedItems(){

        var selecoes = selectedItemsPosition()

        data.removeAll(selecoes)

        notifyDataSetChanged()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var mView: View? = mInflater.inflate(resource, parent, false)

        var ll_background = mView?.findViewById<LinearLayout>(R.id.ll_item)
        var tv_item = mView?.findViewById<TextView>(R.id.item_tv_cell)

        var item = data[position]

        tv_item?.text = "${item[HashMapItems.ITEMCONTENT]}"

        if (item[HashMapItems.STATUS].equals("1")) {
            ll_background?.setBackgroundColor(context.getColor(R.color.selected_on))
        } else {
            ll_background?.setBackgroundColor(context.getColor(R.color.selected_off))
        }

        return mView!!
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position][HashMapItems.ITEM_ID]?.toLong() ?: 0L
    }

    override fun getCount(): Int {
        return data.size
    }


}