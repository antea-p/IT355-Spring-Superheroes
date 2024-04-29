package ac.rs.metropolitan.anteaprimorac5157.controller;

import ac.rs.metropolitan.anteaprimorac5157.entity.Publisher;
import ac.rs.metropolitan.anteaprimorac5157.repository.PublisherRepository;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherRepository publisherRepository;
    private JmsTemplate jmsTemplate;
    private Destination destination;

    @Autowired
    public PublisherController(PublisherRepository publisherRepository, JmsTemplate jmsTemplate, Destination destination) {
        this.publisherRepository = publisherRepository;
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
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

    @GetMapping("/publication")
    public ResponseEntity<Publisher> getNextPublication() {
        MapMessage message = (MapMessage) jmsTemplate.receive(destination);
        try {
            if (message == null) {
                return null;
            }
            Publisher publisher = new Publisher();
            publisher.setId(message.getLong(("publisherId")));
            publisher.setPublisherName(message.getString(("name")));
            return ResponseEntity.ok(publisher);
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
    }

    @GetMapping("/publisher-name/{publisherName}")
    public ResponseEntity<Publisher> findByName(@PathVariable String publisherName) {
        return ResponseEntity.ok(publisherRepository.findByPublisherName(publisherName));
    }

    @PostMapping("/{publisherId}/newPublication")
    public ResponseEntity<Publisher> newPublication(@PathVariable Long publisherId) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);

        return publisherOptional
                .map(publisher -> {
                    sendJmsMessage(publisher);
                    return ResponseEntity.ok(publisher);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private void sendJmsMessage(Publisher publisher) {
        jmsTemplate.send(destination, session -> {
            MapMessage message = session.createMapMessage();
            message.setLong("publisherId", publisher.getId());
            message.setString("name", publisher.getPublisherName());
            return message;
        });
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
