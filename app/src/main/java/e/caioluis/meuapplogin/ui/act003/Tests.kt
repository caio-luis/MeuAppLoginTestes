package e.caioluis.meuapplogin.ui.act003

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import e.caioluis.meuapplogin.R

class Tests : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)

        initVars()
        initActions()
    }

    private fun initVars() {
        context = this@Tests
    }

    private fun initActions() {

    }
}
