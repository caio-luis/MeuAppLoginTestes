package e.caioluis.meuapplogin.ui.act003

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import e.caioluis.meuapplogin.R

class Testes : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testes)

        initVars()
        initActions()
    }

    private fun initVars() {
        context = this@Testes
    }

    private fun initActions() {

    }
}
