package fr.bruno.oilibrary.service.book;

import fr.bruno.oilibrary.model.Book;
import fr.bruno.oilibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing Book entities.
 * It acts as the primary service layer component that handles all book-related operations including
 * creation, retrieval, updating and deletion
 *
 * @author Bruno Maury
 */
@Service
public class BookConfigService {

    private final BookRepository bookRepository;

    public BookConfigService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> list() {
        return bookRepository.findAll();
    }
}
