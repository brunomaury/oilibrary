package fr.bruno.oilibrary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Enumerator used to describe all available types of {@link BaseBook}.
 *
 * @author Bruno Maury
 */
public enum BaseBookType {
    @JsonProperty(Book.TYPE)
    BOOK(Book.class),

    @JsonProperty(Comic.TYPE)
    COMIC(Comic.class);

    private final Class<? extends BaseBook> entityClass;

    BaseBookType(Class<? extends BaseBook> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<? extends BaseBook> getEntityClass() {
        return this.entityClass;
    }

    public static BaseBookType fromJsonValue(String name) {
        return new ObjectMapper().convertValue(name, BaseBookType.class);
    }
}
