package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for interface {@link BookRepository}
 * This class uses Spring Boot Test to configure an isolated test environment
 * for the JPA persistence layer. It ensures that repository
 * operations interact correctly with the database.
 *
 * @author Bruno Maury
 */
class BookRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    private Author authorA;
    private BaseBook bookA;

    @BeforeEach
    void setUp() {
        authorA = new Author();
        authorA.setFirstName("John");
        authorA.setLastName("Doe");
        authorRepository.save(authorA);

        var authorB = new Author();
        authorB.setFirstName("B");
        authorB.setLastName("M");
        authorRepository.save(authorB);

        bookA = new Book();
        bookA.setTitle("Title");
        bookA.setPageCount(100);
        bookA.setAuthor(authorA);
        var bookB = new Comic();
        bookB.setTitle("toto");
        bookB.setPageCount(100);
        bookB.setAuthor(authorB);
        bookRepository.saveAll(List.of(bookA, bookB));
    }

    @Test
    void shouldFindBookByTitle() {
        var title = "Title";
        var list = bookRepository.findByCriteria(
            BookSearchCriteria
                .builder()
                .withTitle(title)
                .build()
        );
        assertThat(list).containsExactly(bookA);
    }

    @Test
    void shouldFindBookByAuthor() {
        var list = bookRepository.findByCriteria(
            BookSearchCriteria
                .builder()
                .withAuthorId(authorA.getId())
                .build()
        );
        assertThat(list).containsExactly(bookA);
    }

    @Test
    void shouldFindBookByType() {
        var list = bookRepository.findByCriteria(
            BookSearchCriteria
                .builder()
                .withType(BaseBookType.BOOK)
                .build()
        );
        assertThat(list).containsExactly(bookA);
    }
}

