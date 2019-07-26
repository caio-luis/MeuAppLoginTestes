package e.caioluis.meuapplogin.model

class HashMapItems(

    var itemid: Long = 1L,
    var content: String = ""

) : HashMap<String, String>() {

    companion object {
        const val ITEM_ID = "id"
        const val ITEMCONTENT = "itemcontent"
        const val STATUS = "status"
    }
}