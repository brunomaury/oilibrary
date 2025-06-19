package fr.bruno.oilibrary.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UuidGenerator;

/**
 * Abstract base class for all database entities in the application.
 * It encapsulates the primary key functionality that all entities should inherit.
 *
 * @author Bruno Maury
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @UuidGenerator
    private String id;

    public String getId() {
        return id;
    }
}
