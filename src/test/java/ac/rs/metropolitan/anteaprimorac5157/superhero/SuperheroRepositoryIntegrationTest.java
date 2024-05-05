package ac.rs.metropolitan.anteaprimorac5157.superhero;

import ac.rs.metropolitan.anteaprimorac5157.entity.*;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SuperheroRepositoryIntegrationTest {

    @Autowired
    private SuperheroRepository superheroRepository;

    @Test
    public void testGetAllSuperheroes() {
        List<Superhero> superheroList = superheroRepository.findAll();
        long expectedResult = superheroRepository.count();
        long actualResult = superheroList.size();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testFindSuperheroById_MatchFound() {
        Optional<Superhero> actualResult = superheroRepository.findById(1L);

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getId()).isEqualTo(1L);
    }

    @Test
    public void testFindSuperheroById_NotFound() {
        Optional<Superhero> actualResult = superheroRepository.findById(999L);
        assertThat(actualResult.isPresent()).isFalse();
    }

    @Test
    public void testFindSuperheroByName() {
        Superhero actualResult = superheroRepository.findBySuperheroName("Batman");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getSuperheroName()).isEqualTo("Batman");
    }

    @Test
    public void testFindByHeightCmGreaterThan() {
        List<Superhero> actualResult = superheroRepository.findByHeightCmGreaterThan(200);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(69);
    }

    @Test
    public void testFindByWeightKgLessThan() {
        List<Superhero> actualResult = superheroRepository.findByWeightKgLessThan(50);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(188);
    }

    @Test
    public void testFindByFullNameNull() {
        List<Superhero> actualResult = superheroRepository.findByFullNameNull();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isNotEqualTo(0);
        actualResult.forEach(superhero -> assertThat(superhero.getFullName()).isNull());
    }

    @Test
    public void testCountByAlignment() {
        int actualResult = superheroRepository.countByAlignment_Alignment("Good");

        assertThat(actualResult).isEqualTo(504);
    }

    @Test
    public void testCountByEyeColour() {
        int actualResult = superheroRepository.countByEyeColour_ColourIn(List.of("Brown", "Blue"));

        assertThat(actualResult).isEqualTo(365);
    }

    @Test
    public void testFindByPublisherAndGender() {
        List<Superhero> actualResult = superheroRepository.findByPublisher_PublisherNameAndGender_Gender("Marvel Comics", "Female");

        assertThat(actualResult).isNotNull();
        actualResult.forEach(superhero -> {
            assertThat(superhero.getPublisher().getPublisherName()).isEqualTo("Marvel Comics");
            assertThat(superhero.getGender().getGender()).isEqualTo("Female");
        });
    }

    @Test
    public void testFindHeroWithMostPowers() {
        Superhero actualResult = superheroRepository.findHeroWithMostPowers();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getSuperheroName()).isEqualTo("Spectre");
    }

    @Test
    public void testFindByFullNameContaining() {
        List<Superhero> actualResult = superheroRepository.findByFullNameContaining("Bruce");

        assertThat(actualResult).isNotNull();
        actualResult.forEach(superhero -> assertThat(superhero.getFullName()).contains("Bruce"));
    }

    @Test
    public void testFindByHeightCmBetween() {
        List<Superhero> actualResult = superheroRepository.findByHeightCmBetween(200, 250);

        assertThat(actualResult).isNotNull();
        actualResult.forEach(superhero -> {
            assertThat(superhero.getHeightCm()).isGreaterThanOrEqualTo(200);
            assertThat(superhero.getHeightCm()).isLessThanOrEqualTo(250);
        });
    }

    @Test
    public void findByPublisher() {
        List<Superhero> actualResult = superheroRepository.findByPublisher_PublisherName("Marvel Comics");

        assertThat(actualResult).isNotNull();
        actualResult.forEach(superhero -> assertThat(superhero.getPublisher().getPublisherName()).isEqualTo("Marvel Comics"));
    }

    @Test
    public void testInsertSuperhero() {
        Superhero newSuperhero = new Superhero(
                "My New Superhero", "His full name",
                new Gender(1L, "Male"),
                new Colour(9L, "Brown"),
                new Colour(13L, "Grey"),
                new Colour(1L, "No Colour"),
                new Race(1L, "-"),
                new Publisher(13L, "Marvel Comics"),
                new Alignment(1L, "Good"),
                188, 90,
                List.of(
                        new Superpower(17L, "Intelligence")
                )
        );

        Superhero savedSuperhero = superheroRepository.save(newSuperhero);
        Superhero retrievedSuperhero = superheroRepository.findById(savedSuperhero.getId()).orElseThrow();

        assertThat(retrievedSuperhero).isEqualTo(savedSuperhero);
    }

    @Test
    public void testUpdateSuperhero() {
        Superhero updatedSuperhero = new Superhero(
                1L,
                "Superman", // promijenjeni atribut
                "Clark Kent",
                new Gender(1L, "Male"),
                new Colour(9L, "Brown"),
                new Colour(13L, "Grey"),
                new Colour(1L, "No Colour"),
                new Race(1L, "-"),
                new Publisher(13L, "Marvel Comics"),
                new Alignment(1L, "Good"),
                188, 90,
                Arrays.asList(
                        new Superpower(1L, "Agility"),
                        new Superpower(18L, "Super Strength"),
                        new Superpower(26L, "Stamina"),
                        new Superpower(31L, "Super Speed")
                )
        );


        Superhero savedSuperhero = superheroRepository.save(updatedSuperhero);
        Superhero retrievedSuperhero = superheroRepository.findById(1L).orElseThrow();

        assertThat(savedSuperhero).isEqualTo(updatedSuperhero);
        assertThat(retrievedSuperhero).isEqualTo(updatedSuperhero);
    }

    @Test
    public void testDeleteSuperhero() {
        Superhero newSuperhero = new Superhero("Hero to Delete",
                "Full name",
                new Gender(1L, "Male"),
                new Colour(9L, "Brown"),
                new Colour(13L, "Grey"),
                new Colour(1L, "No Colour"),
                new Race(1L, "-"),
                new Publisher(13L, "Marvel Comics"),
                new Alignment(1L, "Good"),
                188, 90,
                List.of()
        );
        Superhero savedSuperhero = superheroRepository.save(newSuperhero);
        superheroRepository.deleteById(savedSuperhero.getId());

        Optional<Superhero> actualResult = superheroRepository.findById(savedSuperhero.getId());
        assertThat(actualResult.isPresent()).isFalse();
    }
}
