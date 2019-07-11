package e.caioluis.meuapplogin.model

class ItensHashMap : HashMap<String,String>() {

    var iditem : Long = 1L
    var conteudo : String =""

    companion object {
        const val IDITEM = "id"
        const val CONTEUDOITEM = "conteudoitem"
        const val STATUS = "status"
    }
}