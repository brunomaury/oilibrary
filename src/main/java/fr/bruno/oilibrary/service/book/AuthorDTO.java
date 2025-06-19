package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.Author;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for author entities.
 * It provides a simplified and controlled view of author data without exposing
 * internal entity structure or sensitive information.
 *
 * @author Bruno Maury
 */
public record AuthorDTO(String id, String firstName, String lastName, Date birthday) {
    public AuthorDTO(Author author) {
        this(author.getId(), author.getFirstName(), author.getLastName(), author.getBirthday());
    }
}
