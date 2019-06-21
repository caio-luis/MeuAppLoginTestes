package e.caioluis.meuapplogin.util

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun apagarEditText(editText: EditText) {
    editText.setText("")
}

fun exibirMensagem(context: Context, mensagem: String) {
    Toast.makeText(
        context,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}

fun validarEditText(editText: EditText): Boolean {

    var texto = editText.text.toString()

    if (texto.isEmpty()) {
        return false
    }
    return true
}
