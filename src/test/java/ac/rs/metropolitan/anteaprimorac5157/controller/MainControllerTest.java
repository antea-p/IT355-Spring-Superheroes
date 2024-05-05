package ac.rs.metropolitan.anteaprimorac5157.controller;

import ac.rs.metropolitan.anteaprimorac5157.entity.*;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperheroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainControllerTest {
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

    private SuperheroRepository mockRepository;
    private MainController controller;

    @BeforeEach
    void setUp() {
        mockRepository = mock(SuperheroRepository.class);
        controller = new MainController(mockRepository);
        when(mockRepository.findHeroWithMostPowers()).thenReturn(SUPERMAN);
    }

    @Test
    void testMain() {
        // Nije moguće provjeriti izlaz u konzoli, pa se isključivo provjerava naziv pogleda (view-a)
        String result = controller.getSuperheroes();
        assertEquals("index", result);
    }
}