package fr.bruno.oilibrary.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a comic entity in the library management system.
 *
 * @author Bruno Maury
 */
@Entity
@DiscriminatorValue(Comic.TYPE)
public class Comic extends BaseBook {
    public static final String TYPE = "comic";

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public BaseBookType getBaseBookType() {
        return BaseBookType.COMIC;
    }

    @Override
    public <T> T accept(BookVisitor<T> visitor) {
        return visitor.visitComic(this);
    }
}
