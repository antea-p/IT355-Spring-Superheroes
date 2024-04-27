package ac.rs.metropolitan.anteaprimorac5157.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "colour", schema = "superhero")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String colour;

    public Colour() {}

    public Colour(Long id, String colour) {
        this.id = id;
        this.colour = colour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colour colour1 = (Colour) o;
        return Objects.equals(id, colour1.id) && Objects.equals(colour, colour1.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, colour);
    }
}
