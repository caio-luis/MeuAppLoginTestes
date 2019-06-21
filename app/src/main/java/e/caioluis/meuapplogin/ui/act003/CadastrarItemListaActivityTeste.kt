package e.caioluis.meuapplogin.ui.act003

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import e.caioluis.meuapplogin.R
import e.caioluis.meuapplogin.model.ItemDeListaHashMap
import e.caioluis.meuapplogin.util.exibirMensagem
import e.caioluis.meuapplogin.util.validarEditText
import kotlinx.android.synthetic.main.activity_teste_gerador_de_listas.*

class CadastrarItemListaActivityTeste : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_gerador_de_listas)

        initVars()
        initActions()
    }

    private fun initVars() {
        context = this@CadastrarItemListaActivityTeste
    }

    private fun initActions() {

        teste_gerador_btn_inserir.setOnClickListener {

            if (validarEditText(teste_gerador_et_item)) {

                devolverConteudo()

            } else {

                exibirMensagem(context, "O campo está vazio!")
            }
        }
    }

    fun devolverConteudo (){

        var conteudo = teste_gerador_et_item.text.toString()

        var mIntent = Intent()

        mIntent.putExtra(ItemDeListaHashMap.CONTEUDOITEM, conteudo)

        setResult(Activity.RESULT_OK, mIntent)

        onBackPressed()
    }
}