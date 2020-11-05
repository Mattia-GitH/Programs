package com.SpringH2DB.SpringH2.service;

import com.SpringH2DB.SpringH2.exception.CustomNotFoundException;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public BookEntity createBook(BookEntity bookEntity) {
        return repository.save(bookEntity);
    }

    @Override
    public BookEntity updateBook(BookEntity bookEntity) {
        Optional<BookEntity> bookOpt = repository.findById(bookEntity.getId());
        if (bookOpt.isPresent()) {
            BookEntity bookEntityUpdate = bookOpt.get();
            bookEntityUpdate.setId(bookEntity.getId());
            bookEntityUpdate.setAuthor(bookEntity.getAuthor());
            bookEntityUpdate.setIsbn(bookEntity.getIsbn());
            bookEntityUpdate.setTitle(bookEntity.getTitle());
            repository.save(bookEntityUpdate);
            return bookEntityUpdate;
        } else {
            throw new CustomNotFoundException("Book not found " + bookEntity.getId());
        }
    }

    @Override
    public List<BookEntity> listBook() {
        return repository.findAll();
    }

    @Override
    public BookEntity bookById(long bookId) {
        Optional<BookEntity> bookOpt = repository.findById(bookId);
        if (bookOpt.isPresent()) {
            return bookOpt.get();
        } else {
            throw new CustomNotFoundException("Book not found " + bookId);
        }
    }

    @Override
    public void deleteBook(long bookId) {
        Optional<BookEntity> bookOpt = repository.findById(bookId);
        if (bookOpt.isPresent()) {
            repository.delete(bookOpt.get());
        } else {
            throw new CustomNotFoundException("Book not found " + bookId);
        }
    }
}
