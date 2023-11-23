//문제. 도서관 관리 시스템
//여러분은 도서관의 도서 관리 시스템을 개발하고 있습니다. 이 시스템은 다양한 종류의 도서를 관리하며, 도서의 상태에 따라 다른 처리를 해야 합니다.
//
//Data Class 정의
//
//Book이라는 데이터 클래스를 정의합니다. 이 클래스는 title (문자열), author (문자열), yearPublished (null이 될 수 있는 정수), 그리고 status (Enum 클래스 BookStatus의 인스턴스)를 속성으로 가집니다.
//
//Enum Class 정의
//
//BookStatus라는 Enum 클래스를 정의합니다. 이 클래스는 AVAILABLE, BORROWED, IN_REPAIR, LOST를 상태로 가집니다.
//
//확장 함수 작성
//
//BookStatus에 대한 확장 함수 description을 작성합니다. 이 함수는 각 상태에 대해 적절한 설명을 반환합니다.
//
//엘비스 연산자 사용
//
//Book의 yearPublished가 null인 경우, "출판 연도 미상"으로 처리하는 로직을 작성합니다.
//
//when 사용
//
//Book의 status에 따라 다른 처리를 하는 로직을 when을 사용하여 작성합니다.
//
//구조 분해 선언 사용
//
//Book 객체로부터 title과 author를 구조 분해 선언을 사용하여 추출합니다.
//
//제네릭 함수 작성
//
//여러 종류의 Book 객체들을 받아, 특정 조건에 맞는 책들만을 리스트로 반환하는 제네릭 함수를 작성합니다.
//
//오버로딩된 함수 작성
//
//Book의 정보를 출력하는 함수를 작성합니다. 이 함수는 Book 객체와 Boolean (상세 정보 출력 여부) 두 가지 형태의 파라미터를 받을 수 있도록 오버로딩합니다.
//
//주어진 Book 리스트에서 특정 조건을 만족하는 첫 번째 책을 찾아 출력하는 로직을 작성합니다. break와 continue를 적절히 사용하세요.

enum class BookStatus {
    AVAILABLE, BORROWED, IN_REPAIR, LOST
}

data class Book(
        val title: String,
        val author: String,
        val yearPublished: Int?,
        val status: BookStatus
)

fun BookStatus.description() = when(this) {
    BookStatus.AVAILABLE -> println("이용 가능")
    BookStatus.BORROWED -> println("대출중")
    BookStatus.IN_REPAIR -> println("수리중")
    BookStatus.LOST -> println("분실")
}

fun checkYearPublished(book: Book) {
    println(book.yearPublished ?: "출판 연도 미상")
}

fun printBookInfo(book: Book) {
    println(book.toString())
}

fun printBookInfo(book: Book, status: BookStatus) {
    println(book.toString())
}

fun findBooks(books: List<Book>, status: BookStatus): List<Book> {
    return books.filter { it.status == status }
}

fun main() {
    val books = listOf(
            Book("Kotlin Programming", "John Doe", 2018, BookStatus.AVAILABLE),
            Book("The Joy of Kotlin", "John Smith", null, BookStatus.BORROWED)
    )

    val (title, author) = books[0]
    println("첫 번째 책: $title, 저자: $author")

    for (book in books) {
        if (book.status == BookStatus.LOST) continue
        if (book.status == BookStatus.AVAILABLE) {
            printBookInfo(book)
            break
        }
    }

    val availableBooks = findBooks(books, BookStatus.AVAILABLE)
    println("사용 가능한 책: ${availableBooks.map { it.title }}")
}

