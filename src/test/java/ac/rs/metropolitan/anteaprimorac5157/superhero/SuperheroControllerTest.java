package ac.rs.metropolitan.anteaprimorac5157.superhero;

import ac.rs.metropolitan.anteaprimorac5157.controller.SuperheroController;
import ac.rs.metropolitan.anteaprimorac5157.entity.*;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperheroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SuperheroControllerTest {

    private static final Superhero SUPERMAN = new Superhero(
            1L, // Svi ID-jevi su tipa Long
            "Superman",
            "Clark Kent",
            new Gender(1L, "Male"),
            new Colour(7L, "Blue"),
            new Colour(4L, "Black"),
            new Colour(1L, "No Colour"),
            new Race(37L, "Kryptonian"),
            new Publisher(4L, "DC Comics"),
            new Alignment(1L, "Good"),
            191,
            101,
            new ArrayList<>()
    );
    private static final Superhero BATMAN = new Superhero(
            2L,
            "Batman",
            "Bruce Wayne",
            new Gender(1L, "Male"),
            new Colour(7L, "Blue"),
            new Colour(4L, "Black"),
            new Colour(1L, "No Colour"),
            new Race(24L, "Human"),
            new Publisher(4L, "DC Comics"),
            new Alignment(1L, "Good"),
            188,
            95,
            new ArrayList<>()
    );
    private static final Superhero GOKU = new Superhero(
            3L,
            "Goku",
            "Goku",
            new Gender(1L, "Male"),
            new Colour(7L, "No Colour"),
            new Colour(4L, "No Colour"),
            new Colour(1L, "No Colour"),
            new Race(49L, "Saiyan"),
            new Publisher(17L, "Shueisha"),
            new Alignment(1L, "Good"),
            175,
            62,
            new ArrayList<>()
    );

    private SuperheroRepository mockRepository;
    private SuperheroController superheroController;


    @BeforeEach
    void setUp() {
        this.mockRepository = mock(SuperheroRepository.class);
        this.superheroController = new SuperheroController(mockRepository);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void testGetSuperheroes() {
        List<Superhero> superheroList = List.of(SUPERMAN, BATMAN, GOKU);
        when(mockRepository.findAll()).thenReturn(superheroList);

        ResponseEntity<List<Superhero>> expectedResponse = ResponseEntity.ok(superheroList);
        ResponseEntity<List<Superhero>> actualResponse = superheroController.getSuperheroes();

        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(mockRepository).findAll();
    }


    @Test
    void testFindById_Found() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(SUPERMAN));

        ResponseEntity<Superhero> expectedResponse = ResponseEntity.ok(SUPERMAN);
        ResponseEntity<Superhero> actualResponse = superheroController.findById(1L);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testFindById_NotFound() {
        when(mockRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<?> expectedResponse = ResponseEntity.notFound().build();
        ResponseEntity<?> actualResponse = superheroController.findById(999L);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testSave() {
        Superhero newHero = new Superhero(null, "My Cool New Superhero",
                "Joe",
                new Gender(1L, "Male"),
                new Colour(7L, "Blue"),
                new Colour(4L, "Black"),
                new Colour(1L, "No Colour"),
                new Race(37L, "Kryptonian"),
                new Publisher(4L, "DC Comics"),
                new Alignment(1L, "Good"),
                191,
                101,
                new ArrayList<>());

        Superhero savedHero = new Superhero(4L, "My Cool New Superhero",
                "Joe",
                new Gender(1L, "Male"),
                new Colour(7L, "Blue"),
                new Colour(4L, "Black"),
                new Colour(1L, "No Colour"),
                new Race(24L, "Human"),
                new Publisher(4L, "Microsoft"),
                new Alignment(1L, "Good"),
                191,
                101,
                new ArrayList<>());

        when(mockRepository.save(newHero)).thenReturn(savedHero);

        ResponseEntity<Superhero> expectedResponse = ResponseEntity.ok(savedHero);
        ResponseEntity<Superhero> actualResponse = superheroController.save(newHero);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testUpdate() {
        Superhero SUPERMAN_UPDATED = new Superhero(
                1L,
                "Superman V2", // promijenjeni atribut
                "Clark Kent II",
                 new Gender(1L, "Male"),
                new Colour(7L, "Blue"),
                new Colour(4L, "Black"),
                new Colour(1L, "No Colour"),
                new Race(37L, "Kryptonian"),
                new Publisher(4L, "DC Comics"),
                new Alignment(1L, "Good"),
                191,
                101,
                new ArrayList<>());

        when(mockRepository.save(SUPERMAN_UPDATED)).thenReturn(SUPERMAN_UPDATED);

        ResponseEntity<Superhero> expectedResponse = ResponseEntity.ok(SUPERMAN_UPDATED);
        ResponseEntity<Superhero> actualResponse = superheroController.update(SUPERMAN_UPDATED);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testDeleteById() {
        Long idToDelete = 1L;
        superheroController.deleteById(idToDelete);

        verify(mockRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void testFindByName() {
        when(mockRepository.findBySuperheroName("Superman")).thenReturn(SUPERMAN);

        ResponseEntity<Superhero> expectedResponse = ResponseEntity.ok(SUPERMAN);
        ResponseEntity<Superhero> actualResponse = superheroController.findByName("Superman");

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void testFindByPublisher() {
        List<Superhero> dcComicsHeroesList = List.of(SUPERMAN, BATMAN);
        when(mockRepository.findByPublisher_PublisherName("DC Comics")).thenReturn(dcComicsHeroesList);

        ResponseEntity<List<Superhero>> expectedResponse = ResponseEntity.ok(dcComicsHeroesList);
        ResponseEntity<List<Superhero>> actualResponse = superheroController.findByPublisher("DC Comics");

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}