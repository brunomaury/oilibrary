package fr.bruno.oilibrary.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.bruno.oilibrary.model.BaseBook;
import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.repository.BookSearchCriteria;
import fr.bruno.oilibrary.service.book.BookCommandDTO;
import fr.bruno.oilibrary.service.book.BookConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for class {@link BookResource}
 * This class use Mockito to mock BookConfigService in order to test only BookResource
 * We use WebMvcTest to simulate the webserver
 *
 * @author Bruno Maury
 */
@WebMvcTest(BookResource.class)
public class BookResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookConfigService mockBookConfigService;

    @Test
    void shouldReturnBooks() throws Exception {
        BaseBook bookA = new Book();
        bookA.setTitle("Title");
        bookA.setPageCount(2);
        when(mockBookConfigService.list(
            BookSearchCriteria.builder()
                .withTitle("Title")
                .build()))
            .thenReturn(List.of(bookA));

        mockMvc.perform(get("/api/books")
                .param("title", "Title"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value(bookA.getTitle()))
            .andExpect(jsonPath("$[0].pageCount").value(bookA.getPageCount()));
    }

    @Test
    void shouldCreateBooks() throws Exception {
        BaseBook book = new Book();
        book.setTitle("Title");
        book.setPageCount(1);

        // Any commandDTO works since we mock the service.create
        var bookCommandDTO = new BookCommandDTO("toto", 1, "1");

        when(mockBookConfigService.create(bookCommandDTO))
            .thenReturn(book);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookCommandDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    void shouldFindBook() throws Exception {
        BaseBook book = new Book();
        book.setTitle("Title");
        book.setPageCount(2);
        when(mockBookConfigService.findById("1"))
            .thenReturn(book);

        mockMvc.perform(get("/api/books/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value(book.getTitle()));
    }
}
