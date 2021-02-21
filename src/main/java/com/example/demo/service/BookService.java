package com.example.demo.service;

import com.example.demo.repository.BookRepository;
import com.example.demo.repository.dto.Book;
import com.example.demo.repository.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void registerBook(Book book){
        book.setDeleted(false);
        bookRepository.save(book);
    }

    public Iterable<Book> allBooks (){
        Iterable<Book> books = bookRepository.findAll();
        return books;
    }

    public ArrayList<Book> getIsbn(String isbn){
        Optional<Book> book = bookRepository.findById(isbn);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        return res;
    }

    public ArrayList<Book> getBooksByIsbn(List<String> isbn) {
        return (ArrayList<Book>) bookRepository.findAllById(isbn);
    }

    public void updateBook(String isbn, Book book){
        Book tempBook = bookRepository.findById(isbn).orElseThrow();
        tempBook.setName(book.getName());
        tempBook.setAuthor(book.getAuthor());
        tempBook.setCount(book.getCount());
        bookRepository.save(tempBook);
    }

    public void deleteBook (String isbn){
        Book book = bookRepository.findById(isbn).orElseThrow();
        book.setDeleted(true);
        bookRepository.save(book);
    }
}
