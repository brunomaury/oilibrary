package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.BaseBook;
import fr.bruno.oilibrary.model.BaseBookType;

/**
 * Data Transfer Object (DTO) for BaseBook entities.
 * It contains only information shared by all base book type
 * It provides a simplified and controlled view of book data without exposing
 * internal entity structure or sensitive information.
 *
 * @author Bruno Maury
 */
public record BaseBookLightDTO(String id, String title, int pageCount, BaseBookType type) {
    public BaseBookLightDTO(BaseBook baseBook) {

        this(
            baseBook.getId(),
            baseBook.getTitle(),
            baseBook.getPageCount(),
            baseBook.getBaseBookType()
        );
    }
}
