//package day9
//
//data class Item(val s : String)
//
//// TODO: 아이템을 배포하는 인터페이스 작성 ItemProvider
//interface ItemProvider<T> : Item {
//    fun getItem() {
//
//    }
//}
//
//// TODO: 아이템을 수집하는 인터페이스 작성 ItemCollector
//
//class Transporter<T>(private val provider: ItemProvider<T>, private val collector: ItemCollector<T>) {
//    fun transportItem() {
//        val item = provider.getItem()
//        collector.collectItem(item)
//        println("아이템이 전송되었습니다: $item")
//    }
//}
//
//data class Item(val name: String) // 샘플 아이템 클래스
//
//fun main() {
//    val itemProvider = object : ItemProvider<Item> {
//        override fun getItem(): Item = Item("샘플 아이템")
//    }
//
//    val itemCollector = object : ItemCollector<Item> {
//        override fun collectItem(item: Item) {
//            println("아이템 수집됨: $item")
//        }
//    }
//
//    val transporter = Transporter(itemProvider, itemCollector)
//    transporter.transportItem()
//}