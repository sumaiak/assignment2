package org.example.exercise1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class exercise1 {
    public static class MovieController {
        private List<MovieDto> movies;


        public List<MovieDto> getMoviesByRating(double rating, List<MovieDto> movies) {
            List<MovieDto> highRatedMovies = movies.stream()
                    .filter(movie -> movie.getAverage_vote() >= rating)
                    .collect(Collectors.toList());

            return highRatedMovies;
        }


        public List<MovieDto> getSortedByReleaseDate(List<MovieDto> movies) {
            return movies.stream()
                    .sorted(Comparator.comparing(MovieDto::getReleaseDate))
                    .collect(Collectors.toList());
        }

        public List<MovieDto> searchMoviesByTitle(String title) {
            return movies.stream()
                    .filter(movie -> movie.getTitle().contains(title))
                    .collect(Collectors.toList());
        }
    }
}