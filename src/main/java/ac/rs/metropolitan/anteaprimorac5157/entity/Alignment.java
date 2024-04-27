package ac.rs.metropolitan.anteaprimorac5157.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "alignment", schema = "superhero")
public class Alignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alignment;

    public Alignment() {}

    public Alignment(Long id, String alignment) {
        this.id = id;
        this.alignment = alignment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alignment alignment1 = (Alignment) o;
        return Objects.equals(id, alignment1.id) && Objects.equals(alignment, alignment1.alignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alignment);
    }
}
