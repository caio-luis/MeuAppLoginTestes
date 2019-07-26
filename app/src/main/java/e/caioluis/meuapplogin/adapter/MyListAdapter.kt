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
    private var dados: ArrayList<HashMapItems>

) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    fun doSelection(position: Int) {

        val item = dados[position]

        if (item[HashMapItems.STATUS].equals("1")) {
            item[HashMapItems.STATUS] = "0"

        } else {
            item[HashMapItems.STATUS] = "1"
        }
        notifyDataSetChanged()
    }

    fun selectedItemsNumber(): Int {
        var contador = 0

        for (i in 0 until dados.size) {
            if (dados[i][HashMapItems.STATUS].equals("1")) {
                contador++
            }
        }
        return contador
    }

    fun posicaoDosSelecionados(): ArrayList<HashMapItems> {

        var listaselecionados = ArrayList<HashMapItems>()

        for (i in 0 until dados.size) {
            if (dados[i][HashMapItems.STATUS].equals("1")) {
                listaselecionados.add(dados[i])
            }
        }
        return listaselecionados
    }

    fun deleteSelectedItems(){

        var selecoes = posicaoDosSelecionados()

        dados.removeAll(selecoes)

        notifyDataSetChanged()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var mView: View? = mInflater.inflate(resource, parent, false)

        var ll_fundo = mView?.findViewById<LinearLayout>(R.id.ll_item)
        var tv_item = mView?.findViewById<TextView>(R.id.celula_tv_item)

        var item = dados[position]

        tv_item?.text = "${item[HashMapItems.ITEMCONTENT]}"

        if (item[HashMapItems.STATUS].equals("1")) {
            ll_fundo?.setBackgroundColor(context.getColor(R.color.selected_on))
        } else {
            ll_fundo?.setBackgroundColor(context.getColor(R.color.selected_off))
        }

        return mView!!
    }

    override fun getItem(position: Int): Any {
        return dados[position]
    }

    override fun getItemId(position: Int): Long {
        return dados[position][HashMapItems.ITEM_ID]?.toLong() ?: 0L
    }

    override fun getCount(): Int {
        return dados.size
    }


}