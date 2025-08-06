package fr.bruno.oilibrary.service.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for BookCommand entities.
 * It provides an intermediate object for the API to create a Book without exposing
 * internal entity structure or sensitive information.
 *
 * @author Bruno Maury
 */
public record ComicCommandDTO(
    @NotNull String title,
    @Min(1) int pageCount,
    @NotNull String authorId,
    String color
) implements BaseBookCommandDTO {
}
