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
import e.caioluis.meuapplogin.adapter.AdapterLista
import e.caioluis.meuapplogin.model.ItensHashMap
import e.caioluis.meuapplogin.ui.act003.Testes
import e.caioluis.meuapplogin.util.apagarEditText
import e.caioluis.meuapplogin.util.exibirMensagem
import e.caioluis.meuapplogin.util.validarEditText
import kotlinx.android.synthetic.main.activity_gerador_de_listas.*
import android.widget.SimpleAdapter as SimpleAdapter

class GeradorDeListasActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var adapterLista: AdapterLista

    private var minhalista: ArrayList<ItensHashMap> = ArrayList()

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

                incrementarLista(minhalista)
                exibirLista()
                apagarEditText(gerador_et_item)

            } else {
                exibirMensagem(context, "Erro: O campo não pode estar vazio")
            }
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var mItent = Intent(context, Testes::class.java)

        startActivityForResult(mItent, PARAMETROINTENT)

        return true
    }

    private fun exibirLista() {

        gerador_lv_lista_itens.adapter = adapterLista
    }

    private fun incrementarLista(minhalista: ArrayList<ItensHashMap>): ArrayList<ItensHashMap> {

        var item = gerador_et_item.text.toString()
        var hmAuxiliar = ItensHashMap()

        hmAuxiliar[ItensHashMap.CONTEUDOITEM] = item
        hmAuxiliar[ItensHashMap.IDITEM] = ((minhalista.size) + 1).toString()

        minhalista.add(hmAuxiliar)

        return minhalista
    }

    private fun incrementarLista(minhalista: ArrayList<ItensHashMap>, conteudo: String): ArrayList<ItensHashMap> {

        var hmAuxiliar = ItensHashMap()

        hmAuxiliar[ItensHashMap.CONTEUDOITEM] = conteudo
        hmAuxiliar[ItensHashMap.IDITEM] = ((minhalista.size) + 1).toString()

        minhalista.add(hmAuxiliar)

        return minhalista
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        processarResposta(resultCode, data)
    }

    private fun processarResposta(resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {

            var rConteudo = data?.getStringExtra(ItensHashMap.CONTEUDOITEM)

            incrementarLista(minhalista, rConteudo!!)
            exibirLista()

        } else {
            exibirMensagem(context, "Cancelado")
        }
    }

    companion object {
        const val PARAMETROINTENT = 10
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }
}

