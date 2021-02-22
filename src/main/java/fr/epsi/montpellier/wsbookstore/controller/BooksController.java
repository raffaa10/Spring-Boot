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

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable String id, @Validated @RequestBody Book book) {
        // Vérification des paramètres
        if (!id.equals(book.getIsbn())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "L'id de l'URL ne correspond pas à l'id du body"
            );
        }
        // Recherche du livre
        Book booksUpdated = findById(id);
        // Mise à jour des données
        booksUpdated.updateFrom(book);
        try {
            // Mise à jour dans la BD
            booksUpdated = booksRepository.save(booksUpdated);
            // HTTP Status Code 200 (Ok) par défaut
            LogMessage(String.format("Livre mis à jour: %s", booksUpdated.toString()));
            return booksUpdated;
        } catch (Exception exception) {
            LogError(exception);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Books: %s", book.toString())
            );
        }
    }
    private void LogMessage(String message) {
        System.err.printf("Message=%s, Class=%s\n", message, this.getClass().getCanonicalName());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        boolean success = false;

        // Recherche du livre
        Book book = findById(id);
        try {
            // Suppression
            booksRepository.delete(book);
            LogMessage(String.format("Livre supprimé: %s", book.toString()));
            success = true;
        } catch (Exception exception) {
            LogError(exception);
        }
        return (success ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build());
    }


}
