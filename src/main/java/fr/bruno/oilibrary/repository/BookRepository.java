package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.BaseBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring repository for {@link BaseBook}
 * for links : {\@link Book}
 *
 * @author brunomaury
 */
public interface BookRepository extends JpaRepository<BaseBook, String>, BookRepositoryCustom {
}
