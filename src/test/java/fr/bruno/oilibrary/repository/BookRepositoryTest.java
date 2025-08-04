package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.Author;
import fr.bruno.oilibrary.model.BaseBook;
import fr.bruno.oilibrary.model.Book;
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

    private BaseBook bookA;

    @BeforeEach
    void setUp() {
        var author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        authorRepository.save(author);

        bookA = new Book();
        bookA.setTitle("Title");
        bookA.setPageCount(100);
        bookA.setAuthor(author);
        var bookB = new Book();
        bookB.setTitle("toto");
        bookB.setPageCount(100);
        bookB.setAuthor(author);
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
}

