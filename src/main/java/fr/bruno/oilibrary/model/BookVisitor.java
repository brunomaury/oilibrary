package fr.bruno.oilibrary.model;

/**
 * The BookVisitor interface defines the contract for visiting different parts of a BaseBook structure.
 * Implementations of this interface can provide specific operations to be performed.
 *
 * <p>This pattern is useful for separating algorithms from the object structures they operate on,
 * allowing new operations to be added without modifying the classes of the elements on which they operate.</p>
 *
 * @author brunomaury
 */
public interface BookVisitor<T> {
    T visitBook(Book book);

    T visitComic(Comic comic);
}
