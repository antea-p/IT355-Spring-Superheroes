package ac.rs.metropolitan.anteaprimorac5157.repository;

import ac.rs.metropolitan.anteaprimorac5157.entity.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {
    List<Superhero> findByHeightCmGreaterThan(int heightCm);
    List<Superhero> findByWeightKgLessThan(int weightKg);
    List<Superhero> findByFullNameNull();
    int countByAlignment_Alignment(String alignment);
    int countByEyeColour_ColourIn(List<String> eyeColours);
    List<Superhero> findByPublisher_PublisherNameAndGender_Gender(String publisherName, String gender);
    @Query("SELECT s FROM Superhero s JOIN s.powers p GROUP BY s ORDER BY COUNT(p) DESC LIMIT 1")
    Superhero findHeroWithMostPowers();
    List<Superhero> findByFullNameContaining(String fullName);
    List<Superhero> findByHeightCmBetween(int min, int max);
}
