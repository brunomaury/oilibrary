package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.BaseBook;
import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.model.BookVisitor;
import fr.bruno.oilibrary.model.Comic;
import fr.bruno.oilibrary.repository.AuthorRepository;
import fr.bruno.oilibrary.repository.BookRepository;
import fr.bruno.oilibrary.repository.BookSearchCriteria;
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

    public List<BaseBook> list(BookSearchCriteria criteria) {
        return bookRepository
            .findByCriteria(criteria)
            .stream()
            .sorted(Comparator.comparing(BaseBook::getTitle))
            .toList();
    }

    public BaseBook findById(String id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public BaseBook create(BaseBookCommandDTO baseBookCommandDTO) {
        BaseBook baseBook = switch (baseBookCommandDTO) {
            case BookCommandDTO commandDTO -> new Book();
            case ComicCommandDTO commandDTO -> new Comic();
        };

        copyCommandToBaseBook(baseBookCommandDTO, baseBook);
        bookRepository.save(baseBook);
        return baseBook;
    }

    public void update(String bookId, BaseBookCommandDTO bookCommandDTO) {
        var book = findById(bookId);
        copyCommandToBaseBook(bookCommandDTO, book);
    }

    public void delete(String bookId) {
        var book = findById(bookId);
        bookRepository.delete(book);
    }

    public void copyCommandToBaseBook(BaseBookCommandDTO baseBookCommandDTO, BaseBook baseBook) {

        baseBook.accept(new BookVisitor<Void>() {
            public Void visitBook(Book book) {
                if (!(baseBookCommandDTO instanceof BookCommandDTO)) {
                    throw new IllegalStateException();
                }
                return null;
            }

            public Void visitComic(Comic comic) {
                if (!(baseBookCommandDTO instanceof ComicCommandDTO comicCommandDTO)) {
                    throw new IllegalStateException();
                }
                comic.setColor(comicCommandDTO.color());
                return null;
            }
        });

        baseBook.setTitle(baseBookCommandDTO.title());
        baseBook.setPageCount(baseBookCommandDTO.pageCount());
        baseBook.setAuthor(
            authorRepository
                .findById(baseBookCommandDTO.authorId())
                .orElseThrow(RuntimeException::new)
        );
    }
}
