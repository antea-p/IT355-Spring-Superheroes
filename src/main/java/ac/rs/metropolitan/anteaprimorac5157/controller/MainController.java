package ac.rs.metropolitan.anteaprimorac5157.controller;

import ac.rs.metropolitan.anteaprimorac5157.entity.Superhero;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final SuperheroRepository superheroRepository;

    @Autowired
    public MainController(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    @GetMapping("/superheroes-demo")
    public String getSuperheroes() {
        System.out.println("All Superheroes");
        superheroRepository.findAll().forEach(
                superhero -> System.out.println(superhero.getSuperheroName() + " - " + superhero.getFullName()));

        System.out.println("----------------------------------------------------------------");

        System.out.println("Superheroes with height greater than 200cm");
        superheroRepository.findByHeightCmGreaterThan(200).forEach(
                superhero -> System.out.println(superhero.getSuperheroName() + " - " + superhero.getHeightCm() + "cm"));

        System.out.println("----------------------------------------------------------------");

        System.out.println("Superheroes with weight less than 50kg");
        superheroRepository.findByWeightKgLessThan(50).forEach(
                superhero -> System.out.println(superhero.getSuperheroName() + " - " + superhero.getWeightKG()));

        System.out.println("----------------------------------------------------------------");

        System.out.println("Superheroes with no known true identity (Full name is null in database)");
        superheroRepository.findByFullNameNull().forEach(
                superhero -> System.out.println(superhero.getSuperheroName()));

        System.out.println("----------------------------------------------------------------");

        System.out.println("Number of superheroes with alignment 'Bad'");
        System.out.println(superheroRepository.countByAlignment_Alignment("Bad"));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Number of superheroes with eye colour 'Blue' or 'Brown'");
        System.out.println(superheroRepository.countByEyeColour_ColourIn(List.of("Blue", "Brown")));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Female superheroes published by 'Marvel Comics'");
        superheroRepository.findByPublisher_PublisherNameAndGender_Gender("Marvel Comics", "Female")
                .forEach(superhero -> System.out.println(superhero.getSuperheroName()));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Superhero with most superpowers");
        Superhero mostPowers = superheroRepository.findHeroWithMostPowers();
        System.out.println(mostPowers.getSuperheroName() + " - " + mostPowers.getPowers().size() + " superpowers");
        System.out.println(mostPowers.getSuperheroName() + "'s superpowers:");
        mostPowers.getPowers().forEach(superpower -> System.out.println(superpower.getPowerName()));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Superheroes named John");
        superheroRepository.findByFullNameContaining("John").forEach(
                superhero -> System.out.println(superhero.getSuperheroName() + " - " + superhero.getFullName()));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Superheroes with height between 180cm and 200cm");
        superheroRepository.findByHeightCmBetween(180, 200).forEach(
                superhero -> System.out.println(superhero.getSuperheroName() + " - " + superhero.getHeightCm() + "cm"));

        return "index";
    }
}
