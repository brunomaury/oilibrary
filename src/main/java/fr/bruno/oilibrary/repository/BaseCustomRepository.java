package fr.bruno.oilibrary.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

/**
 * Abstract base class for custom repositories.
 * This class provides a common infrastructure for creating custom repositories
 * that extend Spring Data JPA's standard functionality. It encapsulates the operations
 * Criteria API operations and provides utility methods for building complex dynamic queries.
 * queries.
 * The class uses generics to ensure type-safety and can be extended
 * to create specialized repositories for different entity types.
 *
 * @author brunomaury
 */
public abstract class BaseCustomRepository<T, ID> {

    protected final EntityManager entityManager;
    private final Class<T> domainClass;
    private final SimpleJpaRepository<T, ID> simpleJpaRepository;

    public BaseCustomRepository(
        EntityManager entityManager,
        Class<T> domainClass
    ) {
        this.entityManager = entityManager;
        this.domainClass = domainClass;
        this.simpleJpaRepository = new SimpleJpaRepository<>(domainClass, entityManager);
    }

    Page<T> pageAll(Specification<T> specification, Pageable pageable) {
        return simpleJpaRepository.findAll(specification, pageable);
    }

    List<T> findAll(Specification<T> specification) {
        return simpleJpaRepository.findAll(specification);
    }
}
