package fr.bruno.oilibrary.repository;

/**
 * Immutable data carrier record representing search criteria for BaseBook entities.
 *
 * <p>This record encapsulates all possible search parameters that can be used to filter
 * BaseBook entities in repository queries. It provides a type-safe and immutable way to pass
 * search criteria between different layers of the application (controller, service, repository).</p>
 *
 * <p>The record follows the criteria pattern where each field represents an optional search
 * parameter. Null values indicate that the corresponding criterion should not be applied
 * to the search query, allowing for flexible and dynamic query construction.</p>
 *
 * @author Bruno Maury
 */
public record BookSearchCriteria(String title, String authorId) {
    public BookSearchCriteria(Builder builder) {
        this(builder.title, builder.authorId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String authorId;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthorId(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public BookSearchCriteria build() {
            return new BookSearchCriteria(this);
        }
    }
}
