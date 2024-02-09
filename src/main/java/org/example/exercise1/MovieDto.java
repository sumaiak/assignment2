package org.example.exercise1;

import lombok.Setter;

import java.time.LocalDate;
@Setter

public class MovieDto {

    private String title;
    private String overview;
    LocalDate releaseDate;
    String relaseYear;
    double average_vote;

    public double getAverage_vote() {
        return average_vote;
    }



    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getRelaseYear() {
        return relaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "title='" + title + '\'' +
                ", details='" + overview + '\'' +
                '}';
    }
}
