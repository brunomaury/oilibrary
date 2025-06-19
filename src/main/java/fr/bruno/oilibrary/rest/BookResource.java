package fr.bruno.oilibrary.rest;

import fr.bruno.oilibrary.service.book.BookConfigService;
import fr.bruno.oilibrary.service.book.BookDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides RESTful web service endpoints for performing operations
 * on Book entities.
 *
 * @author Bruno Maury
 */
@RestController
@RequestMapping("/api/books")
public class BookResource {

    private final BookConfigService bookConfigService;

    public BookResource(BookConfigService bookConfigService) {
        this.bookConfigService = bookConfigService;
    }

    @GetMapping
    public List<BookDTO> list() {
        return bookConfigService.list().stream().map(BookDTO::new).toList();
    }
}
