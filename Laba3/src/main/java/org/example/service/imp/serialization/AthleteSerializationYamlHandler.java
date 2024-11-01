package org.example.service.imp.serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.model.Athlete;
import org.example.service.AthleteDataHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AthleteSerializationYamlHandler implements AthleteDataHandler {

    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public void writeAthletes(List<Athlete> athletes, String fileName) {
        var athletesOlder30YearsWithNullSport = athletes.stream()
                .map(a -> {
                    if (a.getAge() > 30) {
                        a.setSport(null);
                    }
                    return a;
                })
                .toList();
       try {
           objectMapper.writeValue(new File(fileName), athletesOlder30YearsWithNullSport);
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
