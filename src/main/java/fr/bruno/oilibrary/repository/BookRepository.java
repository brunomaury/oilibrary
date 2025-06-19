package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
