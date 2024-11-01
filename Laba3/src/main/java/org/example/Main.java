package org.example;

import org.example.model.Athlete;
import org.example.model.Origin;
import org.example.service.AthleteDataHandler;
import org.example.service.imp.BufferedAthleteFileHandler;
import org.example.service.AthleteFileService;
import org.example.service.imp.serialization.AthleteSerializationHandler;
import org.example.service.imp.serialization.AthleteSerializationJsonHandler;
import org.example.service.imp.serialization.AthleteSerializationYamlHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Origin origin1 = new Origin("USA", "New York");
        Origin origin2 = new Origin("Kenya", "Nairobi");
        Origin origin3 = new Origin("Great Britain", "London");

        List<Athlete> athletes = new ArrayList<>();
        athletes.add(new Athlete("John", "Doe", 25, 3, "Sprinter", origin1));
        athletes.add(new Athlete("Jane", "Smith", 31, 5, "Long Distance Runner", origin2));
        athletes.add(new Athlete("Mike", "Johnson", 22, 2, "Jumper", origin3));

        // Ініціалізація обробника файлів

        //AthleteFileService athleteFileHandler = new AthleteFileService(new BufferedAthleteFileHandler());
        //String fileName = "Athletes";
        AthleteDataHandler athleteFileHandler = new AthleteSerializationYamlHandler();
        String fileName = "Athletes.yaml";


        athleteFileHandler.writeAthletes(athletes, fileName);

        List<Athlete> readAthletes;
        try {
            readAthletes = athleteFileHandler.readAthletes(fileName);

            System.out.println("Зчитані атлети:");
            for (Athlete athlete : readAthletes) {
                System.out.println(athlete);
            }


        } catch (IOException e) {
            System.err.println("Помилка при зчитуванні атлетів з файлу: " + e.getMessage());
        }
    }
}
