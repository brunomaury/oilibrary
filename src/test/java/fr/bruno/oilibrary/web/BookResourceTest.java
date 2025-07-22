package fr.bruno.oilibrary.web;

import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.service.book.BookConfigService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for class {@link BookResource}
 * This class use Mockito to mock BookConfigService in order to test only BookResource
 * We use WebMvcTest to simulate the webserver
 *
 * @author Bruno Maury
 */
@WebMvcTest(BookResource.class)
public class BookResourceTest {
    // @Autowired private WebTestClient webTestClient;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookConfigService mockBookConfigService;

    List<Book> books;

    @BeforeEach
    void setUp() {
        Book book = new Book();
        book.setTitle("Title");
        books = List.of(book);
        when(mockBookConfigService.list()).thenReturn(books);
    }

    @Test
    void shouldReturnBooks() throws Exception {
        mockMvc.perform(get("/api/books")
                .param("title", "title"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].title", everyItem(equalTo("Title"))));

    }

}
