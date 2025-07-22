package fr.bruno.oilibrary.web;

import fr.bruno.oilibrary.service.book.BookCommandDTO;
import fr.bruno.oilibrary.service.book.BookConfigService;
import fr.bruno.oilibrary.service.book.BookDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping // post used to create, put to update
    // Deserialisation by jackson to transform body in BookDTO, possible on records
    public BookDTO create(@RequestBody @Validated BookCommandDTO bookCommandDTO) {
        var book = bookConfigService.create(bookCommandDTO);
        return new BookDTO(book);
    }
}