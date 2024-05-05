package ac.rs.metropolitan.anteaprimorac5157.superpower;

import ac.rs.metropolitan.anteaprimorac5157.entity.Superpower;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperpowerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SuperpowerRepositoryIntegrationTest {

    private SuperpowerRepository superpowerRepository;

    @Autowired
    public SuperpowerRepositoryIntegrationTest(SuperpowerRepository superpowerRepository) {
        this.superpowerRepository = superpowerRepository;
    }

    @Test
    void testGetAllSuperpowers() {
        List<Superpower> superpowersList = superpowerRepository.findAll();

        Long expectedResult = superpowerRepository.count();
        Long actualResult = (long) superpowersList.size();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testGetSuperpowerById_MatchFound() {
        Superpower expectedResult = new Superpower(42L, "Magic");
        Optional<Superpower> actualResult = superpowerRepository.findById(42L);

        assertThat(actualResult.isPresent()).isTrue();
        assertThat(actualResult.get()).isEqualTo(expectedResult);
    }

    @Test
    void testGetSuperpowerById_NotFound() {
        Optional<Superpower> expectedResult = Optional.empty();
        Optional<Superpower> actualResult = superpowerRepository.findById(999L);

        assertThat(actualResult.isPresent()).isFalse();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testInsertSuperpower() {
        Superpower newSuperpower = new Superpower("Programming");

        Superpower expectedResult = new Superpower(169L, "Programming");
        Superpower savedActualResult = superpowerRepository.save(newSuperpower);

        assertThat(savedActualResult).isEqualTo(expectedResult);
        assertThat(superpowerRepository.findById(169L)
                .isPresent()).isTrue();
    }

    @Test
    void testUpdateSuperpower() {
        Superpower updatedSuperpower = new Superpower(42L, "Meaning of Life");

        Superpower expectedResult = new Superpower(42L, "Meaning of Life");

        Superpower savedActualResult = superpowerRepository.save(updatedSuperpower);
        Superpower retrievedActualResult = superpowerRepository.findById(42L).orElseThrow();

        assertThat(savedActualResult).isEqualTo(expectedResult);

        assertThat(retrievedActualResult.getId()).isEqualTo(42L);
        assertThat(retrievedActualResult.getPowerName()).isEqualTo("Meaning of Life");
    }

    @Test
    void testDeleteSuperpower() {
        Superpower newSuperpower = new Superpower("Programming");
        superpowerRepository.save(newSuperpower);
        superpowerRepository.deleteById(169L);

        Optional<Superpower> actualResult = superpowerRepository.findById(169L);
        assertThat(actualResult.isPresent()).isEqualTo(false);
    }
}
