package e.caioluis.meuapplogin.model

class ItensHashMap(

    var iditem: Long = 1L,
    var conteudo: String = ""

) : HashMap<String, String>() {

    companion object {
        const val IDITEM = "id"
        const val CONTEUDOITEM = "conteudoitem"
        const val STATUS = "status"
    }
}