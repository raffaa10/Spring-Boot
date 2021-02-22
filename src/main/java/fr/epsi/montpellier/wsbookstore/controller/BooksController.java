package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Book;
import fr.epsi.montpellier.wsbookstore.repository.BooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


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

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@Validated @RequestBody Book book) {
        try {
            Book bookCreated = booksRepository.save(book);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(bookCreated.getIsbn())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception exception) {
            LogError(exception);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Book: %s", book.toString())
            );
        }
    }
    private void LogError(Exception exception) {
        System.err.printf("Error, Class=%s\n", this.getClass().getCanonicalName());
        exception.printStackTrace(System.err);
    }


}
