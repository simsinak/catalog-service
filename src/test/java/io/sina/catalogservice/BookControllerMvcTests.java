package io.sina.catalogservice;

import io.sina.catalogservice.domain.BookNotFoundException;
import io.sina.catalogservice.domain.BookService;
import io.sina.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sinaaskarnejad
 */
@WebMvcTest(BookController.class)
class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    @SuppressWarnings("unused")
    private BookService bookService;
    @Autowired
    private WebTestClient webClient;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "1234567890";
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
        //this is same as the above
        webClient.get()
                .uri("/books/" + isbn)
                .exchange()
                .expectStatus().isNotFound();
    }
}
