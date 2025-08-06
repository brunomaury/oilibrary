package fr.bruno.oilibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a basebook entity in the library management system.
 * there is 2 kind of basebook : book & comic
 *
 * @author Bruno Maury
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "book")
// Never use "instance of" on Entities, use the visitor pattern
public abstract class BaseBook extends BaseEntity {

    @NotNull
    private String title;

    @Min(1)
    private int pageCount;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    // By default, correspond db column is [...]_id
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

    public abstract BaseBookType getBaseBookType();

    public abstract <T> T accept(BookVisitor<T> visitor);

}
