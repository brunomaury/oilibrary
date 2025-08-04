package fr.bruno.oilibrary.service.book;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.model.Comic;

/**
 * Base interface for Book command Data Transfer Objects (DTOs) used in write operations.
 *
 * <p>This interface defines the common contract for all command DTOs related to Book entities,
 * specifically for operations that modify data (create, update, delete). It serves as a
 * foundation for implementing the Command Query Responsibility Segregation (CQRS) pattern
 * by separating command operations from query operations.</p>
 *
 * <p>Command DTOs are used to transfer data from the presentation layer (controllers) to the
 * business logic layer (services) for write operations. They typically contain validation
 * annotations and transformation logic to ensure data integrity before persisting to the database.</p>
 *
 * @author Bruno Maury
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ComicCommandDTO.class, name = Comic.TYPE),
    @JsonSubTypes.Type(value = BookCommandDTO.class, name = Book.TYPE)
})
public sealed interface BaseBookCommandDTO permits BookCommandDTO, ComicCommandDTO {

    String title();

    String authorId();

    int pageCount();

}
