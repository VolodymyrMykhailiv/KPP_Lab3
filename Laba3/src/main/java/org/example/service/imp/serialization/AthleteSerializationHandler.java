package org.example.service.imp.serialization;

import org.example.model.Athlete;
import org.example.service.AthleteDataHandler;

import java.io.*;
import java.util.List;

public class AthleteSerializationHandler implements AthleteDataHandler {
    @Override
    public void writeAthletes(List<Athlete> athletes, String fileName) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(athletes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Athlete> readAthletes(String fileName) throws IOException {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Athlete>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
