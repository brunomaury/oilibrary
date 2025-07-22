package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring repository for {@link Book}
 * for links : {\@link Book}
 * @author brunomaury
 */
public interface BookRepository extends JpaRepository<Book, String> {
}
