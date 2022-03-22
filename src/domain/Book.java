package domain;

import java.util.List;

public class Book {

    private String name;
    private String isbn;
    private Publisher publisher;
    private List<Chapter> chapters;

    public Book() {}

    public Book(String isbn, String name, Publisher publisher) {
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Book {" +
                "book='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher=" + publisher +
                ", chapters=" + chapters;
    }
}
