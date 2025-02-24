package io.sina.catalogservice.domain;

import java.util.Optional;

/**
 * @author sinaaskarnejad
 */
public interface BookRepository {

    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
