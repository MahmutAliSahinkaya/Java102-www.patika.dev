import java.util.Comparator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        TreeSet<Book> book1 = new TreeSet<>();


        Book bk1 = new Book("Yüzüklerin Efendisi - I - Yüzük Kardeşliği", 496, "J. R. R. Tolkien", 2000);
        Book bk2 = new Book("Yüzüklerin Efendisi - II - İki Kule", 416, "J. R. R. Tolkien", 2014);
        Book bk3 = new Book("Yüzüklerin Efendisi - III - Kralın Dönüşü", 408, "J. R. R. Tolkien", 2015);
        Book bk4 = new Book("Silmarillion", 512, "J. R. R. Tolkien", 2022);
        Book bk5 = new Book("Hobbit", 336, "J. R. R. Tolkien", 2017);

        book1.add(bk1);
        book1.add(bk2);
        book1.add(bk3);
        book1.add(bk4);
        book1.add(bk5);


        for (Book bookName : book1) {
            System.out.println("Book Name: " + bookName.getBookName() +
                    "\nNumber Of Page: " + bookName.getNumberOfPages() +
                    "\nAuthor Name: " + bookName.getAuthorName() +
                    "\nRelease Date: " + bookName.getReleaseDate());
            System.out.println("################################################");
        }


        TreeSet<Book> book2 = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getNumberOfPages() - b2.getNumberOfPages();
            }

        });

        book2.add(bk1);
        book2.add(bk2);
        book2.add(bk3);
        book2.add(bk4);
        book2.add(bk5);


        for (Book bookPage : book2) {
            System.out.println("Book Name: " + bookPage.getBookName() +
                    "\nNumber Of Page: " + bookPage.getNumberOfPages() +
                    "\nAuthor Name: " + bookPage.getAuthorName() +
                    "\nRelease Date: " + bookPage.getReleaseDate());
            System.out.println("################################################");
        }
    }
}