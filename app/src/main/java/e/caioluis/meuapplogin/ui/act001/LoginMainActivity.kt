package e.caioluis.meuapplogin.ui.act001

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import e.caioluis.meuapplogin.R
import e.caioluis.meuapplogin.ui.act002.ListMakerActivity

class LoginMainActivity : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        initVars()
        initActions()

    }

    private fun initVars() {
        context = this@LoginMainActivity
    }

    private fun initActions() {

        val mIntent = Intent(context, ListMakerActivity::class.java)

        startActivity(mIntent)

        finish()
    }
}
