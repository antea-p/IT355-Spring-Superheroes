package ac.rs.metropolitan.anteaprimorac5157.superpower;

import ac.rs.metropolitan.anteaprimorac5157.entity.Superpower;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperpowerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// Alternativni način testiranja repozitorija
@DataJpaTest
public class SuperpowerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SuperpowerRepository repository;

    @Test
    public void testCreateAndFindById() {
        Superpower newPower = new Superpower(null, "Invisibility");
        newPower = entityManager.persistFlushFind(newPower);
        Optional<Superpower> found = repository.findById(newPower.getId());
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getPowerName()).isEqualTo("Invisibility");
    }

    @Test
    public void testUpdate() {
        Superpower power = new Superpower(null, "Flight");
        power = entityManager.persistFlushFind(power);
        power.setPowerName("Super Flight");
        repository.save(power);

        Superpower updated = entityManager.find(Superpower.class, power.getId());
        assertThat(updated.getPowerName()).isEqualTo("Super Flight");
    }

    @Test
    public void testDelete() {
        Superpower power = new Superpower(null, "Strength");
        power = entityManager.persistFlushFind(power);
        repository.deleteById(power.getId());
        assertThat(entityManager.find(Superpower.class, power.getId())).isNull();
    }

    @Test
    public void testAutoIncrementSequence() {
        Superpower newSuperpower = new Superpower(null, "Test Power");
        Superpower savedSuperpower = repository.save(newSuperpower);
        entityManager.flush();
        entityManager.clear();
        assertThat(savedSuperpower.getId()).isEqualTo(169L);
    }

}