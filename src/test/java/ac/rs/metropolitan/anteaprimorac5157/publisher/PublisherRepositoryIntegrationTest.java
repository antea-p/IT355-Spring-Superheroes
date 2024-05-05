package ac.rs.metropolitan.anteaprimorac5157.publisher;

import ac.rs.metropolitan.anteaprimorac5157.entity.Publisher;
import ac.rs.metropolitan.anteaprimorac5157.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PublisherRepositoryIntegrationTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void testGetAllPublishers() {
        List<Publisher> publishersList = publisherRepository.findAll();

        Long expectedResult = publisherRepository.count();
        Long actualResult = (long) publishersList.size();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testFindPublisherById_MatchFound() {
        Optional<Publisher> expectedResult = Optional.of(new Publisher(14L, "Microsoft"));
        Optional<Publisher> actualResult = publisherRepository.findById(14L);

        assertThat(actualResult).isPresent();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testFindPublisherById_NotFound() {
        Optional<Publisher> expectedResult = Optional.empty();
        Optional<Publisher> actualResult = publisherRepository.findById(999L);

        assertThat(actualResult.isPresent()).isFalse();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testFindPublisherByName() {
        Publisher expectedResult = new Publisher(2L, "ABC Studios");
        Publisher actualResult = publisherRepository.findByPublisherName("ABC Studios");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testInsertPublisher() {
        Publisher newPublisher = new Publisher("New Publisher");

        Publisher expectedResult = publisherRepository.save(newPublisher);
        Publisher savedActualResult = publisherRepository.findById(expectedResult.getId()).orElseThrow();

        assertThat(savedActualResult).isEqualTo(expectedResult);
        assertThat(publisherRepository.findById(26L)
                .isPresent()).isTrue();
    }

    @Test
    public void testUpdatePublisher() {
        Publisher updatedPublisher = new Publisher(1L, "Updated Name");

        Publisher expectedResult = new Publisher(1L, "Updated Name");

        Publisher savedActualResult = publisherRepository.save(updatedPublisher);
        Publisher retrievedActualResult = publisherRepository.findById(1L).orElseThrow();

        assertThat(savedActualResult).isEqualTo(expectedResult);

        assertThat(retrievedActualResult.getId()).isEqualTo(1L);
        assertThat(retrievedActualResult.getPublisherName()).isEqualTo("Updated Name");

        assertThat(savedActualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testDeletePublisher() {
        Publisher newPublisher = new Publisher("New Publisher");
        publisherRepository.save(newPublisher);
        publisherRepository.deleteById(27L);

        Optional<Publisher> actualResult = publisherRepository.findById(27L);
        assertThat(actualResult.isPresent()).isFalse();
    }
}
