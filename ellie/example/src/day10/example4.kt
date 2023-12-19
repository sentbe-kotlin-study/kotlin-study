package day10

import kotlin.reflect.KProperty

// 위임 속성, lazy 초기화 사용
class Order {
    // 사용자 정의 위임 속성을 사용하여 상품 가격 변경 기록
    private var _itemPrices: Map<String, Double> by PriceChangeLogging(mapOf())

    // TODO: 주문한 상품 목록을 처음 액세스할 때만 실제로 상품 정보를 로드하도록 구현하세요. (lazy 초기화)
    // 구현할 메서드 이름: loadOrderedItems
    val orderedItems by lazy { loadOrderedItems() }

    // TODO: 주문한 총 가격을 계산하는 함수를 구현하세요.
    // 구현할 메서드 이름: calculateTotalPrice
    fun calculateTotalPrice(): Double {
        return orderedItems.sumOf { item -> _itemPrices.getValue(item.name) * item.quantity }
    }

    // 상품 정보 로드 시뮬레이션
    private fun loadOrderedItems(): List<OrderedItem> {
        return listOf(
            OrderedItem("ProductA", 2),
            OrderedItem("ProductB", 3),
            OrderedItem("ProductC", 1)
        )
    }
}

data class OrderedItem(val name: String, val quantity: Int)

// 사용자 정의 위임 속성을 사용하여 가격 변경 내역 기록
class PriceChangeLogging(initialPrices: Map<String, Double>) {
    private var prices: Map<String, Double> = initialPrices

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Map<String, Double> {
        return prices
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Map<String, Double>) {
        val oldPrices = prices
        prices = value
        logPriceChanges(oldPrices, value)
    }

    private fun logPriceChanges(oldPrices: Map<String, Double>, newPrices: Map<String, Double>) {
        for ((product, oldPrice) in oldPrices) {
            val newPrice = newPrices[product]
            if (oldPrice != newPrice) {
                println("가격 변경: $product, 이전 가격: $oldPrice, 새로운 가격: $newPrice")
            }
        }
    }
}

fun main() {
    // Example usage:
    val order = Order()
    order.orderedItems // 처음 액세스 시 상품 정보 로드됨
    val totalPrice = order.calculateTotalPrice()
    println("주문 총 가격: $totalPrice")
}
