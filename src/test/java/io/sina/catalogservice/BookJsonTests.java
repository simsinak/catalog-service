package io.sina.catalogservice;

import io.sina.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {

    @Autowired
    JacksonTester<Book> jacksonTester;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Sample Book", "Sina", 12.5);
        var jsonContent = jacksonTester.write(book);
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent)
                .extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                "isbn": "1234567890",
                "title": "Sample Book",
                "author": "Sina",
                "price": 12.5
                }
                """;
        assertThat(jacksonTester.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Sample Book", "Sina", 12.5));
    }

}
