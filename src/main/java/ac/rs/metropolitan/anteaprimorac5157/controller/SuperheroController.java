package ac.rs.metropolitan.anteaprimorac5157.controller;

import ac.rs.metropolitan.anteaprimorac5157.entity.Superhero;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superhero")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class SuperheroController {
    private final SuperheroRepository superheroRepository;

    @Autowired
    public SuperheroController(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    @GetMapping
    public ResponseEntity<List<Superhero>> getSuperheroes() {
        return ResponseEntity.ok(superheroRepository.findAll());
    }

    @GetMapping("/{superheroId}")
    public ResponseEntity<Superhero> findById(@PathVariable Long superheroId) {
        return superheroRepository.findById(superheroId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Superhero> save(@RequestBody Superhero superhero) {
        return ResponseEntity.ok(superheroRepository.save(superhero));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Superhero> update(@RequestBody Superhero superhero) {
        return ResponseEntity.ok(superheroRepository.save(superhero));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{superheroId}")
    public void deleteById(@PathVariable Long superheroId) {
        superheroRepository.deleteById(superheroId);
    }

    @GetMapping("/superhero-name/{superheroName}")
    public ResponseEntity<Superhero> findByName(@PathVariable String superheroName) {
        return ResponseEntity.ok(superheroRepository.findBySuperheroName(superheroName));
    }

    @GetMapping("/published-by/{publisherName}")
    public ResponseEntity<List<Superhero>> findByPublisher(@PathVariable String publisherName) {
        return ResponseEntity.ok(superheroRepository.findByPublisher_PublisherName(publisherName));
    }
}
