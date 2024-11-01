package org.example.service.imp.serialization;

import org.example.model.Athlete;
import org.example.model.Origin;
import org.example.service.AthleteDataHandler;
import org.example.service.AthleteFileService;
import org.example.service.imp.BufferedAthleteFileHandler;
import org.example.service.imp.SimpleAthleteFileHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AthleteSerializationHandlerTest {
    private final String testFileName = "testAthletes";

    @AfterEach
    void tearDown() {
        for (String ext : new String[]{".ser", ".json", ".yaml"}) {
            File testFile = new File(testFileName + ext);
            if (testFile.exists()) {
                testFile.delete();
            }
        }
    }

    static Stream<AthleteDataHandler> athleteDataHandlerProvider() {
        return Stream.of(
                new AthleteSerializationHandler(),
                new AthleteSerializationJsonHandler(),
                new AthleteSerializationYamlHandler(),
                new SimpleAthleteFileHandler(),
                new BufferedAthleteFileHandler()
        );
    }

    @ParameterizedTest
    @MethodSource("athleteDataHandlerProvider")
    void testWriteAthletes(AthleteDataHandler handler) {


        Origin origin1 = new Origin("USA", "New York");
        Origin origin2 = new Origin("Kenya", "Nairobi");
        Origin origin3 = new Origin("Great Britain", "London");

        List<Athlete> athletes = new ArrayList<>();
        athletes.add(new Athlete("John", "Doe", 25, 3, "Sprinter", origin1));
        athletes.add(new Athlete("Jane", "Smith", 31, 5, "Long Distance Runner", origin2));
        athletes.add(new Athlete("Mike", "Johnson", 22, 2, "Jumper", origin3));



        handler.writeAthletes(athletes, testFileName );


        File testFile = new File(testFileName );
        assertTrue(testFile.exists());
    }

    @ParameterizedTest
    @MethodSource("athleteDataHandlerProvider")
    void testReadAthletes(AthleteDataHandler handler) throws IOException {


        Origin origin1 = new Origin("USA", "New York");
        Origin origin2 = new Origin("Kenya", "Nairobi");
        Origin origin3 = new Origin("Great Britain", "London");

        List<Athlete> athletesToWrite = new ArrayList<>();
        athletesToWrite.add(new Athlete("John", "Doe", 25, 3, "Sprinter", origin1));
        athletesToWrite.add(new Athlete("Jane", "Smith", 31, 5, "Long Distance Runner", origin2));
        athletesToWrite.add(new Athlete("Mike", "Johnson", 22, 2, "Jumper", origin3));

        handler.writeAthletes(athletesToWrite, testFileName);

        List<Athlete> athletesRead = handler.readAthletes(testFileName);


        assertEquals(3, athletesRead.size());
        assertEquals("John", athletesRead.get(0).getName());
        assertEquals("Doe", athletesRead.get(0).getSurname());
        assertEquals(25, athletesRead.get(0).getAge());
        assertEquals(0, athletesRead.get(0).getMedalCount());
        assertEquals("USA", athletesRead.get(0).getOrigin().getCountry());
    }
}
