package ac.rs.metropolitan.anteaprimorac5157.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "race", schema = "superhero")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String race;

    public Race() {}

    public Race(Long id, String race) {
        this.id = id;
        this.race = race;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race1 = (Race) o;
        return Objects.equals(id, race1.id) && Objects.equals(race, race1.race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, race);
    }
}
