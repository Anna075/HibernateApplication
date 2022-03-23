package client;

import domain.Book;
import service.BookStoreService;

public class RetrieveBookClient {
    public static void main(String[] args) {
        BookStoreService bookStoreService = new BookStoreService();
        Book book = bookStoreService.retrieveObjectGraph("95477867301983");
        System.out.println(book);
    }
}