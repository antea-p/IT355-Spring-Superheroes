package ac.rs.metropolitan.anteaprimorac5157.controller;

import ac.rs.metropolitan.anteaprimorac5157.entity.Publisher;
import ac.rs.metropolitan.anteaprimorac5157.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getPublishers() {
        return ResponseEntity.ok(publisherRepository.findAll());
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<Publisher> findById(@PathVariable Long publisherId) {
        return publisherRepository.findById(publisherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/publisher-name/{publisherName}")
    public ResponseEntity<Publisher> findByName(@PathVariable String publisherName) {
        return ResponseEntity.ok(publisherRepository.findByPublisherName(publisherName));
    }

    @PostMapping
    public ResponseEntity<Publisher> save(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherRepository.save(publisher));
    }

    @PutMapping
    public ResponseEntity<Publisher> update(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherRepository.save(publisher));
    }

    @DeleteMapping("/{publisherId}")
    public void deleteById(@PathVariable Long publisherId) {
        publisherRepository.deleteById(publisherId);
    }
}
