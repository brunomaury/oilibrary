package fr.bruno.oilibrary.service.book;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.bruno.oilibrary.model.BaseBook;
import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.model.BookVisitor;
import fr.bruno.oilibrary.model.Comic;

/**
 * Base interface for Book Data Transfer Objects (DTOs).
 * Used to add the book type in the json serialisation.
 *
 * @author Bruno Maury
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(
        value = ComicDTO.class,
        name = Comic.TYPE
    ),
    @JsonSubTypes.Type(
        value = BookDTO.class,
        name = Book.TYPE
    )
})
public sealed interface BaseBookDTO permits BookDTO, ComicDTO {
    static BaseBookDTO create(BaseBook baseBook) {
        return baseBook.accept(
            new BookVisitor<BaseBookDTO>() {
                @Override
                public BookDTO visitBook(Book book) {
                    return new BookDTO(book);
                }

                @Override
                public ComicDTO visitComic(Comic comic) {
                    return new ComicDTO(comic);
                }
            });
    }
}