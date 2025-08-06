package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.Author;
import fr.bruno.oilibrary.model.BaseBook;
import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.repository.AuthorRepository;
import fr.bruno.oilibrary.repository.BookRepository;
import fr.bruno.oilibrary.repository.BookSearchCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for class {@link BookConfigService}
 * This class uses Mockito to mock repositories in order to only test BookConfigService
 *
 * @author Bruno Maury
 */
@ExtendWith(MockitoExtension.class)
class BookConfigServiceTest {
    @Mock
    private BookRepository mockBookRepository;
    @Mock
    private AuthorRepository mockAuthorRepository;
    @InjectMocks
    private BookConfigService bookConfigService;
    //    private Author author;
    @Captor
    private ArgumentCaptor<BaseBook> bookCaptor;

    @Test
    void shouldFindByTitle() {
        var bookA = new Book();
        bookA.setTitle("Book A");
        var bookB = new Book();
        bookB.setTitle("Book B");
        var bookSearchCriteria = BookSearchCriteria
            .builder()
            .withTitle("Book")
            .build();
        when(mockBookRepository.findByCriteria(bookSearchCriteria)).thenReturn(List.of(bookB, bookA));

        assertThat(bookConfigService.list(bookSearchCriteria)).containsExactly(bookA, bookB);
    }

    @Test
    void shouldCreate() {
        var author = new Author();
        author.setId("1");
        when(mockAuthorRepository.findById(author.getId())).thenReturn(Optional.of(author));

        var bookCommandDTO = new BookCommandDTO("title", 1, "1");
        bookConfigService.create(bookCommandDTO);
        verify(mockBookRepository).save(bookCaptor.capture());
        assertThat(bookCaptor.getValue().getTitle()).isEqualTo(bookCommandDTO.title());
        assertThat(bookCaptor.getValue().getPageCount()).isEqualTo(bookCommandDTO.pageCount());
        assertThat(bookCaptor.getValue().getAuthor()).isEqualTo(author);
    }
}