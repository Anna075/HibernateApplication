package service;

import domain.Book;
import domain.Chapter;
import domain.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookStoreService {

    public void persistObjectGraph(Book book) {

        try{ Class.forName("com.mysql.cj.jdbc.Driver");
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "password");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO PUBLISHER (CODE, PUBLISHER_NAME) VALUES(?,?)");

            statement.setString(1, book.getPublisher().getCode());
            statement.setString(2, book.getPublisher().getName());
            statement.executeUpdate();

            statement.close();

            statement = connection.prepareStatement("INSERT INTO BOOK (ISBN, BOOK_NAME, PUBLISHER_CODE) VALUES(?, ?, ?)");
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getName());
            statement.setString(3, book.getPublisher().getCode());
            statement.executeUpdate();

            statement.close();

            statement = connection.prepareStatement("INSERT INTO CHAPTER (BOOK_ISBN, CHAPTER_NUM, TITLE) VALUES (?, ?, ?)");
            for(Chapter chapter : book.getChapters()){
                statement.setString(1, book.getIsbn());
                statement.setInt(2, chapter.getChapterNumber());
                statement.setString(3, chapter.getTitle());
                statement.executeUpdate();
            }
            statement.close();
        } catch(ClassNotFoundException | SQLException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
    }

    public Book retrieveObjectGraph(String isbn){
        Book book = null;
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "password");){
            Class.forName("com.mysql.cj.jdbc.Driver");

            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM BOOK, PUBLISHER WHERE BOOK.PUBLISHER_CODE = PUBLISHER.CODE AND BOOK.ISBN =?");
            statement.setString(1, isbn);

            ResultSet resultSet = statement.executeQuery();

            book = new Book();

            if(resultSet.next()){
                book.setIsbn(resultSet.getString("ISBN"));
                book.setName(resultSet.getString("BOOK_NAME"));

                Publisher publisher = new Publisher();
                publisher.setCode(resultSet.getString("CODE"));
                publisher.setName(resultSet.getString("PUBLISHER_NAME"));
                book.setPublisher(publisher);
            }

            resultSet.close();
            statement.close();

            List<Chapter> chapters = new ArrayList<>();
            statement = connection.prepareStatement("SELECT * FROM CHAPTER WHERE BOOK_ISBN = ?");
            statement.setString(1, isbn);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                Chapter chapter = new Chapter();
                chapter.setTitle(resultSet.getString("TITLE"));
                chapter.setChapterNumber(resultSet.getInt("CHAPTER_NUM"));
                chapters.add(chapter);
            }
            book.setChapters(chapters);
            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return book;
    }
}
