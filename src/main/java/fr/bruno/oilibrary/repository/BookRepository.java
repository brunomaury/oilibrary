package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring repository for {@link Book}
 * for links : {\@link Book}
 *
 * @author brunomaury
 */
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE CONCAT('%',LOWER(:title),'%')")
    List<Book> findByTitle(String title);
}
