package fr.bruno.oilibrary.service.author;

import fr.bruno.oilibrary.model.Author;
import fr.bruno.oilibrary.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing Author entities.
 * It acts as the primary service layer component that handles all author-related operations including
 * creation, retrieval, updating and deletion
 *
 * @author Bruno Maury
 */
@Service
public class AuthorConfigService {
    private final AuthorRepository authorRepository;

    public AuthorConfigService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> list() {
        return authorRepository.findAll();
    }
}
