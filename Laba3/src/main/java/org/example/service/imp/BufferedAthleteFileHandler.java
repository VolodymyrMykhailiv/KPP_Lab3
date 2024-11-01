package org.example.service.imp;

import org.example.model.Athlete;
import org.example.service.AthleteDataHandler;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BufferedAthleteFileHandler implements AthleteDataHandler {
    @Override
    public void writeAthletes(List<Athlete> athletes, String fileName) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            for (Athlete athlete : athletes) {
                String row = athlete.toStringRepresentation() + "\n";
                outputStream.write(row.getBytes());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Athlete> readAthletes(String fileName) throws IOException {
        List<Athlete> athletsList = new ArrayList<>();

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            int byteRead;

            while ((byteRead = inputStream.read()) != -1) {
                char symbol = (char) byteRead;

                if (symbol == '\n') {
                    String line = stringBuilder.toString();
                    Athlete athlete = Athlete.createFromString(line);
                    athletsList.add(athlete);
                    stringBuilder.setLength(0);
                } else {
                    stringBuilder.append(symbol);
                }
            }

            if (!stringBuilder.isEmpty()) {
                String line = stringBuilder.toString();
                Athlete athlete = Athlete.createFromString(line);
                athletsList.add(athlete);
            }
        }

        return athletsList;
    }


}
