package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.BaseBook;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

/**
 * Implementation of custom repository methods for advanced BaseBook search operations.
 * This class provides the concrete implementation of {@link BookRepositoryCustom} interface
 * using JPA Criteria API to build dynamic queries.
 * The implementation uses the Criteria API to construct SQL queries dynamically based on
 * the provided search criteria, ensuring optimal performance by only including non-null
 * search parameters in the WHERE clause.
 *
 * @author brunomaury
 */
public class BookRepositoryCustomImpl extends BaseCustomRepository<BaseBook, String> implements BookRepositoryCustom {

    public BookRepositoryCustomImpl(EntityManager entityManager) {
        super(entityManager, BaseBook.class);
    }

    @Override
    public List<BaseBook> findByCriteria(BookSearchCriteria criteria) {
        return findAll(buildSpecification(criteria));
    }

    private Specification<BaseBook> buildSpecification(BookSearchCriteria criteria) {
        return ((root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            if (hasText(criteria.title())) {
                predicates.add(cb.like(
                    cb.lower(root.get("title")),
                    "%" + criteria.title().toLowerCase() + "%"
                ));
            }

            if (hasText(criteria.authorId())) {
                predicates.add(cb.equal(root.get("author").get("id"), criteria.authorId()));
            }

            if (criteria.type() != null) {
                predicates.add(cb.equal(root.type(), criteria.type().getEntityClass()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
