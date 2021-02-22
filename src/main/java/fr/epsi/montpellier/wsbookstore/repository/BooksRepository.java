package fr.epsi.montpellier.wsbookstore.repository;

import fr.epsi.montpellier.wsbookstore.models.Book;
import org.springframework.data.repository.CrudRepository;


public interface BooksRepository extends CrudRepository<Book, String> {



}
