package io.sina.catalogservice.domain;

/**
 * @author sinaaskarnejad
 */
public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("A Book with ISBN " + isbn + " already exists");
    }
}
