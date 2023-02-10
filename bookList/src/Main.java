import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Kitap 1", 120, "Yazar 1", "2022-01-01"));
        books.add(new Book("Kitap 2", 90, "Yazar 2", "2021-12-01"));
        books.add(new Book("Kitap 3", 150, "Yazar 3", "2021-11-01"));
        books.add(new Book("Kitap 4", 50, "Yazar 4", "2021-10-01"));
        books.add(new Book("Kitap 5", 200, "Yazar 5", "2021-09-01"));
        books.add(new Book("Kitap 6", 75, "Yazar 6", "2021-08-01"));
        books.add(new Book("Kitap 7", 135, "Yazar 7", "2021-07-01"));
        books.add(new Book("Kitap 8", 95, "Yazar 8", "2021-06-01"));
        books.add(new Book("Kitap 9", 115, "Yazar 9", "2021-05-01"));
        books.add(new Book("Kitap 10", 130, "Yazar 10", "2021-04-01"));

        // Kitap ismi ve yazar ismini içeren Map oluşturma
        Map<String, String> bookAuthorMap = books.stream().collect(Collectors.toMap(Book::getBookName, Book::getAuthor));
        System.out.println(bookAuthorMap);

        // Sayfa sayısı 100'den fazla olan kitapları filtreleme
        List<Book> filteredBooks = books.stream().filter(b -> b.getPageCount() > 100).collect(Collectors.toList());
        System.out.println(filteredBooks);
    }
}
