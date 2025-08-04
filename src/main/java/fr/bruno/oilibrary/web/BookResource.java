package fr.bruno.oilibrary.web;

import fr.bruno.oilibrary.repository.BookSearchCriteria;
import fr.bruno.oilibrary.service.book.BaseBookCommandDTO;
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
    public List<BookDTO> list(@RequestParam(required = false) String title, String authorId) {
        var bookSearchCriteria = BookSearchCriteria
            .builder()
            .withTitle(title)
            .withAuthorId(authorId)
            .build();

        return bookConfigService.list(bookSearchCriteria).stream().map(BookDTO::new).toList();
    }

    @PostMapping // post used to create, put to update
    // Deserialisation by jackson to transform body in BookDTO, possible on records
    public BookDTO create(@RequestBody @Validated BaseBookCommandDTO baseBookCommandDTO) {
        var book = bookConfigService.create(baseBookCommandDTO);
        return new BookDTO(book);
    }

    @PutMapping
    public void update(@RequestParam(required = true) String id, @RequestBody @Validated BaseBookCommandDTO baseBookCommandDTO) {
        bookConfigService.update(id, baseBookCommandDTO);
    }
}