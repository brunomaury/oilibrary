package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.Comic;

/**
 * Data Transfer Object (DTO) for Comic entities.
 * It provides a simplified and controlled view of comic data without exposing
 * internal entity structure or sensitive information.
 *
 * @author Bruno Maury
 */
public record ComicDTO(String id, String title, int pageCount, String color) implements BaseBookDTO {
    public ComicDTO(Comic comic) {

        this(
            comic.getId(),
            comic.getTitle(),
            comic.getPageCount(),
            comic.getColor()
        );
    }
}
