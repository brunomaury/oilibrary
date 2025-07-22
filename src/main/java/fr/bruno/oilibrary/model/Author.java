package fr.bruno.oilibrary.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * Represents a book author entity in the library management system.
 *
 * @author Bruno Maury
 */
@Entity
public class Author extends BaseEntity {

    @NotNull

    private String firstName;
    @NotNull
    private String lastName;

    private Date birthday;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
