package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    BookEntity createBook(BookEntity bookEntity);

    BookEntity updateBook(BookEntity bookEntity);

    List<BookEntity> listBook();

    BookEntity bookById(long bookId);

    void deleteBook(long id);
}
