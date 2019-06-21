package e.caioluis.meuapplogin.ui.act001

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import e.caioluis.meuapplogin.R
import e.caioluis.meuapplogin.ui.act002.GeradorDeListasActivity
import kotlinx.android.synthetic.main.activity_login_telainicial.*

class TelaIinicialLoginActivity : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_telainicial)

        initVars()
        initActions()

    }

    private fun initVars() {
        context = this@TelaIinicialLoginActivity
    }

    private fun initActions() {

        login_btn_entrar.setOnClickListener {

            var mIntent = Intent(context, GeradorDeListasActivity::class.java)

            startActivity(mIntent)

            finish()
        }
    }
}