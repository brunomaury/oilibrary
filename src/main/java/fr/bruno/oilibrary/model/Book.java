package fr.bruno.oilibrary.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a book entity in the library management system.
 *
 * @author Bruno Maury
 */
@Entity
@DiscriminatorValue(Book.TYPE)
public class Book extends BaseBook {
    public static final String TYPE = "book";

    @Override
    public BaseBookType getBaseBookType() {
        return BaseBookType.BOOK;
    }

    @Override
    public <T> T accept(BookVisitor<T> visitor) {
        return visitor.visitBook(this);
    }
}
