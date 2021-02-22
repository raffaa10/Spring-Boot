package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class BooksController {

    // http://localhost/api/books

    @GetMapping("/books")
    public List<Book> getBook() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("123457", 1, "MobyDick" ));
        list.add(new Book("1234589", 2, "Germinal"));
        return list;
    }

}
