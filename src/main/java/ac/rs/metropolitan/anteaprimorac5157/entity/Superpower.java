package ac.rs.metropolitan.anteaprimorac5157.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "superpower", schema = "superhero")
public class Superpower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String powerName;

    public Superpower() {}

    public Superpower(Long id, String powerName) {
        this.id = id;
        this.powerName = powerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superpower powerName1 = (Superpower) o;
        return Objects.equals(id, powerName1.id) && Objects.equals(powerName, powerName1.powerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, powerName);
    }
}
