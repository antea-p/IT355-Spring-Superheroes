package ac.rs.metropolitan.anteaprimorac5157.controller;

import ac.rs.metropolitan.anteaprimorac5157.entity.Superpower;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperpowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superpower")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class SuperpowerController {
    private final SuperpowerRepository superpowerRepository;

    @Autowired
    public SuperpowerController(SuperpowerRepository superpowerRepository) {
        this.superpowerRepository = superpowerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Superpower>> getSuperpowers() {
        return ResponseEntity.ok(superpowerRepository.findAll());
    }

    @GetMapping("/{superpowerId}")
    public ResponseEntity<Superpower> findById(@PathVariable Long superpowerId) {
        return superpowerRepository.findById(superpowerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Superpower> save(@RequestBody Superpower superpower) {
        return ResponseEntity.ok(superpowerRepository.save(superpower));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Superpower> update(@RequestBody Superpower superpower) {
        return ResponseEntity.ok(superpowerRepository.save(superpower));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{superpowerId}")
    public void deleteById(@PathVariable Long superpowerId) {
        superpowerRepository.deleteById(superpowerId);
    }
}
