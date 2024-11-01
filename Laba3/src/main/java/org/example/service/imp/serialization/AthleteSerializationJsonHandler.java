package org.example.service.imp.serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Athlete;
import org.example.service.AthleteDataHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AthleteSerializationJsonHandler implements AthleteDataHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void writeAthletes(List<Athlete> athletes, String fileName) {
       try {
           objectMapper.writeValue(new File(fileName), athletes);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

    }

    @Override
    public List<Athlete> readAthletes(String fileName) throws IOException {
        return objectMapper.readValue(new File(fileName), new TypeReference<>() {
        });
    }
}
