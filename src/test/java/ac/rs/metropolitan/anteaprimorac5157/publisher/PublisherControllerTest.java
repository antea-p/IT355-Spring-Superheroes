package ac.rs.metropolitan.anteaprimorac5157.publisher;

import ac.rs.metropolitan.anteaprimorac5157.controller.PublisherController;
import ac.rs.metropolitan.anteaprimorac5157.entity.Publisher;
import ac.rs.metropolitan.anteaprimorac5157.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.MapMessage;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PublisherControllerTest {

    private PublisherRepository mockRepository;
    private JmsTemplate mockJmsTemplate;
    private Destination mockDestination;
    private PublisherController publisherController;
    private static final Publisher MARVEL_PUBLISHER = new Publisher(1L, "Marvel");
    private static final Publisher DC_PUBLISHER = new Publisher(2L, "DC");

    @BeforeEach
    void setUp() {
        mockRepository = mock(PublisherRepository.class);
        mockJmsTemplate = mock(JmsTemplate.class);
        mockDestination = mock(Destination.class);
        publisherController = new PublisherController(mockRepository, mockJmsTemplate, mockDestination);
    }

    @Test
    void testGetPublishers() {
        List<Publisher> publishers = List.of(MARVEL_PUBLISHER, DC_PUBLISHER);

        when(mockRepository.findAll()).thenReturn(publishers);

        ResponseEntity<List<Publisher>> expectedResponse = ResponseEntity.ok(publishers);
        ResponseEntity<List<Publisher>> actualResponse = publisherController.getPublishers();

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testFindById_Found() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(MARVEL_PUBLISHER));

        ResponseEntity<Publisher> expectedResponse = ResponseEntity.ok(MARVEL_PUBLISHER);
        ResponseEntity<Publisher> actualResponse = publisherController.findById(1L);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testFindById_NotFound() {
        when(mockRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<?> expectedResponse = ResponseEntity.notFound().build();
        ResponseEntity<?> actualResponse = publisherController.findById(999L);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testFindByName() {
        when(mockRepository.findByPublisherName("Marvel")).thenReturn(MARVEL_PUBLISHER);

        ResponseEntity<Publisher> expectedResponse = ResponseEntity.ok(MARVEL_PUBLISHER);
        ResponseEntity<Publisher> actualResponse = publisherController.findByName("Marvel");

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testGetNextPublication() throws Exception {
        MapMessage mockMessage = mock(MapMessage.class);
        when(mockMessage.getLong("publisherId")).thenReturn(3L);
        when(mockMessage.getString("name")).thenReturn("Shueisha");
        when(mockJmsTemplate.receive(mockDestination)).thenReturn(mockMessage);

        Publisher expectedPublisher = new Publisher(3L, "Shueisha");
        ResponseEntity<Publisher> expectedResponse = ResponseEntity.ok(expectedPublisher);
        ResponseEntity<Publisher> actualResponse = publisherController.getNextPublication();

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }


    @Test
    void testNewPublication() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(MARVEL_PUBLISHER));
        doNothing().when(mockJmsTemplate).send(eq(mockDestination), any(MessageCreator.class));

        ResponseEntity<Publisher> expectedResponse = ResponseEntity.ok(MARVEL_PUBLISHER);
        ResponseEntity<Publisher> actualResponse = publisherController.newPublication(1L);

        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(mockJmsTemplate).send(eq(mockDestination), any(MessageCreator.class));
    }


    @Test
    void testSave() {
        Publisher newPublisher = new Publisher(null, "New Publisher");
        Publisher savedPublisher = new Publisher(3L, "New Publisher");
        when(mockRepository.save(newPublisher)).thenReturn(savedPublisher);

        ResponseEntity<Publisher> expectedResponse = ResponseEntity.ok(savedPublisher);
        ResponseEntity<Publisher> actualResponse = publisherController.save(newPublisher);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testUpdate() {
        Publisher DC_COMICS_UPDATED = new Publisher(2L, "DC Comics V2");
        when(mockRepository.save(DC_COMICS_UPDATED)).thenReturn(DC_COMICS_UPDATED);

        ResponseEntity<Publisher> expectedResponse = ResponseEntity.ok(DC_COMICS_UPDATED);
        ResponseEntity<Publisher> actualResponse = publisherController.update(DC_COMICS_UPDATED);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testDeleteById() {
        Long idToDelete = 2L;
        doNothing().when(mockRepository).deleteById(idToDelete);

        publisherController.deleteById(idToDelete);

        verify(mockRepository, times(1)).deleteById(idToDelete);
    }
}
