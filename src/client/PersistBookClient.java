package client;

import domain.Book;
import domain.Chapter;
import domain.Publisher;
import service.BookStoreService;

import java.util.ArrayList;
import java.util.List;

public class PersistBookClient {
    public static void main(String[] args) {
        BookStoreService bookStoreService = new BookStoreService();
        Publisher publisher = new Publisher("ABC", "ABC Publications Co.");
        Book book = new Book("95477867301983", "ABC, Second Edition", publisher);

        List<Chapter> chapters = new ArrayList<>();
        Chapter chapterOne = new Chapter("Introduction into ABC", 1);
        chapters.add(chapterOne);
        Chapter chapterTwo = new Chapter("Abc Models", 2);
        chapters.add(chapterTwo);

        book.setChapters(chapters);
        bookStoreService.persistObjectGraph(book);
    }
}
