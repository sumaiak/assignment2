package org.example.exercise1;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class exercise1Test {
    @BeforeEach
    void setUp() {
        List<String> titlesToSearch = Arrays.asList(
                "The Shawshank Redemption",
                "The Godfather",
                "The Dark Knight",
                "The Godfather: Part II",
                "The Lord of the Rings: The Return of the King",
                "Pulp Fiction",
                "12 Angry Men",
                "The Good, the Bad and the Ugly",
                "Forrest Gump",
                "Fight Club",
                "Inception"
        );

    }


}