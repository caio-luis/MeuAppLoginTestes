package e.caioluis.meuapplogin.util

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun cleanEditText(editText: EditText) {
    editText.setText("")
}

fun showMessage(context: Context, mensagem: Int) {
    Toast.makeText(
        context,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}

fun validateEditText(editText: EditText): Boolean {

    var texto = editText.text.toString()

    if (texto.isEmpty()) {
        return false
    }
    return true
}
