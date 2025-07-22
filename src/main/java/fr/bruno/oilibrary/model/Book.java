package fr.bruno.oilibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a book entity in the library management system.
 *
 * @author Bruno Maury
 */
@Entity
// Never use "instance of" on Entities, use the visitor pattern
public class Book extends BaseEntity {
    @NotNull
    private String title;

    @Min(1)
    private int pageCount;

    @NotNull
    @ManyToOne()
    // By default, correspond db column is ..._id
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
