package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.repository.AuthorRepository;
import fr.bruno.oilibrary.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * Service class responsible for managing Book entities.
 * It acts as the primary service layer component that handles all book-related operations including
 * creation, retrieval, updating and deletion
 *
 * @author Bruno Maury
 */
@Service
@Transactional
public class BookConfigService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookConfigService(
        BookRepository bookRepository,
        AuthorRepository authorRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> list() {
        return bookRepository
            .findAll()
            .stream()
            .sorted(Comparator.comparing(Book::getTitle))
            .toList();
    }

    public Book create(BookCommandDTO bookCommandDTO) {
        Book book = new Book();
        book.setTitle(bookCommandDTO.title());
        book.setPageCount(bookCommandDTO.pageCount());
        book.setAuthor(
            authorRepository
                .findById(bookCommandDTO.authorId())
                .orElseThrow(RuntimeException::new)
        );
        return bookRepository.save(book);
    }
}
