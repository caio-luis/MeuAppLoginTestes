package e.caioluis.meuapplogin.model

class HashMapItems(

    var iditem: Long = 1L,
    var conteudo: String = ""

) : HashMap<String, String>() {

    companion object {
        const val ITEM_ID = "id"
        const val ITEMCONTENT = "conteudoitem"
        const val STATUS = "status"
    }
}