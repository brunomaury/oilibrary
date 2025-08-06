package fr.bruno.oilibrary.web;

import fr.bruno.oilibrary.service.author.AuthorConfigService;
import fr.bruno.oilibrary.service.author.AuthorDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class provides RESTful web service endpoints for performing operations
 * on Authors entities.
 *
 * @author Bruno Maury
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorResource {

    private final AuthorConfigService authorConfigService;

    public AuthorResource(AuthorConfigService authorConfigService) {
        this.authorConfigService = authorConfigService;
    }

    @GetMapping
    public List<AuthorDTO> list() {
        return authorConfigService.list().stream().map(AuthorDTO::new).toList();
    }
}