package fr.bruno.oilibrary.web;

import fr.bruno.oilibrary.model.BaseBookType;
import fr.bruno.oilibrary.repository.BookSearchCriteria;
import fr.bruno.oilibrary.service.book.BaseBookCommandDTO;
import fr.bruno.oilibrary.service.book.BaseBookDTO;
import fr.bruno.oilibrary.service.book.BaseBookLightDTO;
import fr.bruno.oilibrary.service.book.BookConfigService;
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

    @GetMapping("/{bookId}")
    public BaseBookDTO get(@PathVariable String bookId) {
        var baseBook = bookConfigService.findById(bookId);
        return BaseBookDTO.create(baseBook);
    }

    @GetMapping
    public List<BaseBookLightDTO> list(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String authorId,
        @RequestParam(required = false) BaseBookType type
    ) {
        var bookSearchCriteria = BookSearchCriteria
            .builder()
            .withTitle(title)
            .withAuthorId(authorId)
            .withType(type)
            .build();

        return bookConfigService.list(bookSearchCriteria).stream().map(BaseBookLightDTO::new).toList();
    }

    @PostMapping // post used to create, put to update
    // Deserialisation by jackson to transform body in BookDTO, possible on records
    public BaseBookDTO create(@RequestBody @Validated BaseBookCommandDTO baseBookCommandDTO) {
        var baseBook = bookConfigService.create(baseBookCommandDTO);
        return BaseBookDTO.create(baseBook);
    }

    @PutMapping
    public void update(@RequestParam(required = true) String id, @RequestBody @Validated BaseBookCommandDTO baseBookCommandDTO) {
        bookConfigService.update(id, baseBookCommandDTO);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable String bookId) {
        bookConfigService.delete(bookId);
    }
}