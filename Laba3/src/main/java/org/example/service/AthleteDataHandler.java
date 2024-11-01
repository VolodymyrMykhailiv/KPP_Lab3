package org.example.service;

import org.example.model.Athlete;

import java.io.IOException;
import java.util.List;

public interface AthleteDataHandler {
    void writeAthletes(List<Athlete> athletes, String fileName);
    List<Athlete> readAthletes(String fileNAme) throws IOException;
}
