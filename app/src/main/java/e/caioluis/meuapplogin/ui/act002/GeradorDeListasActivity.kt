package e.caioluis.meuapplogin.ui.act002

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import e.caioluis.meuapplogin.R
import e.caioluis.meuapplogin.adapter.AdapterLista
import e.caioluis.meuapplogin.model.ItemDeListaHashMap
import e.caioluis.meuapplogin.ui.act001.TelaIinicialLoginActivity
import e.caioluis.meuapplogin.ui.act003.CadastrarItemListaActivityTeste
import e.caioluis.meuapplogin.util.apagarEditText
import e.caioluis.meuapplogin.util.exibirMensagem
import e.caioluis.meuapplogin.util.validarEditText
import kotlinx.android.synthetic.main.activity_gerador_de_listas.*
import android.widget.SimpleAdapter as SimpleAdapter

class GeradorDeListasActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var adapterLista: AdapterLista

    private var minhalista: ArrayList<ItemDeListaHashMap> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerador_de_listas)

        initVars()
        initActions()
    }

    private fun initVars() {

        context = this@GeradorDeListasActivity

        adapterLista = AdapterLista(
            context,
            R.layout.celula_item_lista,
            minhalista
        )
    }

    private fun initActions() {

        gerador_btn_inserir.setOnClickListener {

            if (validarEditText(gerador_et_item)) {

                gerarLista(minhalista)
                formarLista()
                apagarEditText(gerador_et_item)

            } else {
                exibirMensagem(context, "O campo está vazio!")
            }
        }

        gerador_btn_inserir_tela2.setOnClickListener {

            var mIntent = Intent(context, CadastrarItemListaActivityTeste::class.java)

            startActivityForResult(mIntent, PARAMETROINTENT)
        }

        gerador_btn_exluir.setOnClickListener {

            adapterLista.excluirSelecionados()

            gerador_btn_exluir.isVisible = false

        }

        gerador_lv_lista_itens.setOnItemClickListener { parent, view, position, id ->

            adapterLista.fazerSelecao(position)

            gerador_btn_exluir.isVisible =
                adapterLista.numeroDeItensSelecionados() > 0
        }
    }

    private fun processarResposta(resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {

            var rConteudo = data?.getStringExtra(ItemDeListaHashMap.CONTEUDOITEM)

            gerarLista(minhalista, rConteudo!!)
            formarLista()

        } else {
            exibirMensagem(context, "Cancelado")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        processarResposta(resultCode, data)
    }

    private fun formarLista() {

        gerador_lv_lista_itens.adapter = adapterLista
    }

    private fun gerarLista(minhalista: ArrayList<ItemDeListaHashMap>): ArrayList<ItemDeListaHashMap> {

        var item = gerador_et_item.text.toString()
        var hmAuxiliar = ItemDeListaHashMap()

        hmAuxiliar[ItemDeListaHashMap.CONTEUDOITEM] = item
        hmAuxiliar[ItemDeListaHashMap.IDITEM] = ((minhalista.size) + 1).toString()

        minhalista.add(hmAuxiliar)

        return minhalista
    }

    private fun gerarLista(minhalista: ArrayList<ItemDeListaHashMap>, conteudo: String): ArrayList<ItemDeListaHashMap> {

        var hmAuxiliar = ItemDeListaHashMap()

        hmAuxiliar[ItemDeListaHashMap.CONTEUDOITEM] = conteudo
        hmAuxiliar[ItemDeListaHashMap.IDITEM] = ((minhalista.size) + 1).toString()

        minhalista.add(hmAuxiliar)

        return minhalista
    }

    override fun onBackPressed() {

        var mIntent = Intent(context, TelaIinicialLoginActivity::class.java)

        startActivity(mIntent)

        finish()
    }

    companion object {
        const val PARAMETROINTENT = 10
    }
}

