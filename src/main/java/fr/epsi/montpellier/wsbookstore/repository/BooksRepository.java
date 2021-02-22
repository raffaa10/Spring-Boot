package fr.epsi.montpellier.wsbookstore.repository;

import fr.epsi.montpellier.wsbookstore.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface BooksRepository extends CrudRepository<Book, String> {

    Optional<Book> findByTitle(String titre);

    Iterable<Book> findByEdition(String edition);

    Iterable<Book> findByPriceGreaterThan(Double price);

}
