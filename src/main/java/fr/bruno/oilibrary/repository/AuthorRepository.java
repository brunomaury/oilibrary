package fr.bruno.oilibrary.repository;

import fr.bruno.oilibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository  extends JpaRepository<Author, String> {
}
