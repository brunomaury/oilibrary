package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.Book;

/**
 * Data Transfer Object (DTO) for Book entities.
 * It provides a simplified and controlled view of book data without exposing
 * internal entity structure or sensitive information.
 *
 * @author Bruno Maury
 */
public record BookDTO(String id, String title, int pageCount) implements BaseBookDTO {
    public BookDTO(Book book) {

        this(
            book.getId(),
            book.getTitle(),
            book.getPageCount()
        );
    }
}
