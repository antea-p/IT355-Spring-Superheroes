package ac.rs.metropolitan.anteaprimorac5157.superpower;

import ac.rs.metropolitan.anteaprimorac5157.controller.SuperpowerController;
import ac.rs.metropolitan.anteaprimorac5157.entity.Superpower;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperpowerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SuperpowerControllerTest {

    private SuperpowerRepository mockRepository;
    private SuperpowerController superpowerController;

    public static final Superpower AGILITY_SUPERPOWER = new Superpower(1L, "Agility");
    public static final Superpower MAGIC_SUPERPOWER = new Superpower(2L, "Magic");


    @BeforeEach
    void setUp() {
        this.mockRepository = mock(SuperpowerRepository.class);
        this.superpowerController = new SuperpowerController(mockRepository);
    }

    @Test
    void testGetSuperpowers() {
        List<Superpower> supowerpowersList = List.of(AGILITY_SUPERPOWER, MAGIC_SUPERPOWER);
        when(mockRepository.findAll()).thenReturn(supowerpowersList);

        ResponseEntity<List<Superpower>> expectedResponse = ResponseEntity.ok(supowerpowersList);
        ResponseEntity<List<Superpower>> actualResponse = superpowerController.getSuperpowers();

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testFindByID_MatchFound() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(AGILITY_SUPERPOWER));

        ResponseEntity<Superpower> expectedResponse = ResponseEntity.ok(AGILITY_SUPERPOWER);
        ResponseEntity<Superpower> actualResponse = superpowerController.findById(1L);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testFindByID_NoMatch() {
        when(mockRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<Superpower> expectedResponse = ResponseEntity.notFound().build();
        ResponseEntity<Superpower> actualResponse = superpowerController.findById(99999L);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testSave() {
        Superpower newSuperpower = new Superpower(null, "My Cool New Superpower");
        Superpower savedSuperpower = new Superpower(3L, "My Cool New Superpower");
        when(mockRepository.save(newSuperpower)).thenReturn(savedSuperpower);

        ResponseEntity<Superpower> expectedResponse = ResponseEntity.ok(savedSuperpower);
        ResponseEntity<Superpower> actualResponse = superpowerController.save(newSuperpower);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testUpdate() {
        Superpower AGILITY_UPDATED = new Superpower(1L, "Agility V2");

        when(mockRepository.save(AGILITY_UPDATED)).thenReturn(AGILITY_UPDATED);

        ResponseEntity<Superpower> expectedResponse = ResponseEntity.ok(AGILITY_UPDATED);
        ResponseEntity<Superpower> actualResponse = superpowerController.update(AGILITY_UPDATED);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testDelete() {
        superpowerController.deleteById(2L);
        verify(mockRepository, times(1)).deleteById(2L);

    }
}
