package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.Author;
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

    @BeforeEach
    void setUp() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Title");
        book.setPageCount(100);
        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Test
    void shouldFindBookByTitle() {
        var title = "Title";
        List<Book> list = bookRepository.findByTitle(title);
        // TODO : Why this warning ?
        assertThat(list.stream().allMatch(book -> book.getTitle().contains(title)));
    }
}

