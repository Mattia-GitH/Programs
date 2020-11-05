package com.SpringH2DB.SpringH2.controller;


import com.SpringH2DB.SpringH2.Convert.BookConvert;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.Book;
import com.SpringH2DB.SpringH2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookConvert bookConvert;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> listBooks() {
        List<BookEntity> list = bookService.listBook();
        return ResponseEntity.ok().body(bookConvert.listBooks(list));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> bookByID(@PathVariable Long id) {
        BookEntity bookEntity = bookService.bookById(id);
        return ResponseEntity.ok().body(bookConvert.toBookModel(bookEntity));
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        BookEntity bookEntity = bookConvert.toBookEntity(book);
        bookEntity = bookService.createBook(bookEntity);
        return ResponseEntity.ok().body(bookConvert.toBookModel(bookEntity));
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        BookEntity bookEntity = bookService.bookById(id);
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setIsbn(book.getIsbn());
        return ResponseEntity.ok().body(bookConvert.toBookModel(bookEntity));
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }
}
