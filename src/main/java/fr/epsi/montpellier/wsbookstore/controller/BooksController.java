package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Book;
import fr.epsi.montpellier.wsbookstore.repository.BooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
public class BooksController {

    private BooksRepository booksRepository;

    public BooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return booksRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBooks(@PathVariable(value = "id") String id) {
        return findById(id);
    }

    private Book findById(String isbn) {
        return booksRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Livre '%s' non trouvé", isbn))
                );
    }


}
