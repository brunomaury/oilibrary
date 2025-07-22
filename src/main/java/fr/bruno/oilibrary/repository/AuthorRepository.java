package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring repository for {@link Author}
 * for links : {\@link Author}
 *
 * @author brunomaury
 */
public interface AuthorRepository extends JpaRepository<Author, String> {
}
