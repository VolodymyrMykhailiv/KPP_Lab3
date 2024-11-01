package org.example.service;

import org.example.model.Athlete;

import java.io.*;
import java.util.List;

public class AthleteFileService {
    private final AthleteDataHandler athleteFileHandler;

    public AthleteFileService(AthleteDataHandler athleteFileHandler) {
        this.athleteFileHandler = athleteFileHandler;
    }

    public void writeAthletes(List<Athlete> athletes, String fileName) {
        athleteFileHandler.writeAthletes(athletes, fileName);
    }

    public List<Athlete> readAthletes(String fileName) throws IOException {
       return athleteFileHandler.readAthletes(fileName);
    }

}
