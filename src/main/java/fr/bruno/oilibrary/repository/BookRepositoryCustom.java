package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.BaseBook;

import java.util.List;

/**
 * Custom repository interface for advanced BookSearchCriteria search operations.
 * This interface defines custom query methods that extend the standard Spring Data JPA
 * repository functionality for the {@link BookSearchCriteria} entity.
 * The implementation of this interface should use JPA Criteria API or custom JPQL
 * queries to handle complex search scenarios involving multiple optional parameters,
 * dynamic filtering, and advanced querying logic.
 *
 * @author brunomaury
 */
public interface BookRepositoryCustom {
    List<BaseBook> findByCriteria(BookSearchCriteria criteria);
}
