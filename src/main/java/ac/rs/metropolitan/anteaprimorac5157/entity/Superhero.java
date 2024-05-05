package ac.rs.metropolitan.anteaprimorac5157.entity;

import ac.rs.metropolitan.anteaprimorac5157.CollectionUtils;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "superhero", schema = "superhero")
public class Superhero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String superheroName;
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "eye_colour_id", nullable = false)
    private Colour eyeColour;

    @ManyToOne
    @JoinColumn(name = "hair_colour_id", nullable = false)
    private Colour hairColour;

    @ManyToOne
    @JoinColumn(name = "skin_colour_id", nullable = false)
    private Colour skinColour;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "alignment_id", nullable = false)
    private Alignment alignment;

    private Integer heightCm;
    private Integer weightKg;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "hero_power",
            schema = "superhero",
            joinColumns = @JoinColumn(name = "hero_id"),
            inverseJoinColumns = @JoinColumn(name = "power_id")
    )
    private List<Superpower> powers;

    public Superhero() {}

    public Superhero(String superheroName, String fullName, Gender gender, Colour eyeColour, Colour hairColour, Colour skinColour, Race race, Publisher publisher, Alignment alignment, Integer heightCm, Integer weightKg, List<Superpower> powers) {
        this.superheroName = superheroName;
        this.fullName = fullName;
        this.gender = gender;
        this.eyeColour = eyeColour;
        this.hairColour = hairColour;
        this.skinColour = skinColour;
        this.race = race;
        this.publisher = publisher;
        this.alignment = alignment;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.powers = powers;
    }

    public Superhero(Long id, String superheroName, String fullName, Gender gender, Colour eyeColour, Colour hairColour, Colour skinColour,
                     Race race, Publisher publisher, Alignment alignment, int heightCm, int weightKg, List<Superpower> powers) {
        this.id = id;
        this.superheroName = superheroName;
        this.fullName = fullName;
        this.gender = gender;
        this.eyeColour = eyeColour;
        this.hairColour = hairColour;
        this.skinColour = skinColour;
        this.race = race;
        this.publisher = publisher;
        this.alignment = alignment;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.powers = powers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuperheroName() {
        return superheroName;
    }

    public void setSuperheroName(String superheroName) {
        this.superheroName = superheroName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Colour getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(Colour eyeColour) {
        this.eyeColour = eyeColour;
    }

    public Colour getHairColour() {
        return hairColour;
    }

    public void setHairColour(Colour hairColour) {
        this.hairColour = hairColour;
    }

    public Colour getSkinColour() {
        return skinColour;
    }

    public void setSkinColour(Colour skinColour) {
        this.skinColour = skinColour;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    public int getWeightKG() {
        return weightKg;
    }

    public void setWeightKG(int weightKg) {
        this.weightKg = weightKg;
    }

    public List<Superpower> getPowers() {
        return powers;
    }

    public void setPowers(List<Superpower> powers) {
        this.powers = powers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superhero superhero = (Superhero) o;
        return Objects.equals(id, superhero.id) && Objects.equals(superheroName, superhero.superheroName) && Objects.equals(fullName, superhero.fullName) && Objects.equals(gender, superhero.gender) && Objects.equals(eyeColour, superhero.eyeColour) && Objects.equals(hairColour, superhero.hairColour) && Objects.equals(skinColour, superhero.skinColour) && Objects.equals(race, superhero.race) && Objects.equals(publisher, superhero.publisher) && Objects.equals(alignment, superhero.alignment) && Objects.equals(heightCm, superhero.heightCm) && Objects.equals(weightKg, superhero.weightKg) && CollectionUtils.equals(powers, superhero.powers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, superheroName, fullName, gender, eyeColour, hairColour, skinColour, race, publisher, alignment, heightCm, weightKg, powers);
    }

    @Override
    public String toString() {
        return "Superhero{" +
                "id=" + id +
                ", superheroName=" + superheroName +
                ", fullName=" + fullName +
                ", gender=" + gender +
                ", eyeColour=" + eyeColour +
                ", hairColour=" + hairColour +
                ", skinColour=" + skinColour +
                ", race=" + race +
                ", publisher=" + publisher +
                ", alignment=" + alignment +
                ", heightCm=" + heightCm +
                ", weightKg=" + weightKg +
                ", powers=" + powers +
                ", weightKG=" + getWeightKG() +
                '}';
    }
}
