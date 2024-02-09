package org.example.exercise1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String apiUrl = "https://api.themoviedb.org/3/discover/movie";
        String apiKey = "fdd59691b3227cda47bd5c6f0adfb8c1";
        exercise1.MovieController n = new exercise1.MovieController();

        String url = apiUrl + "?api_key=" + apiKey;

        String json = geTitle(url);

        List<MovieDto> movies = parseJsonToMovie(json);
        List<MovieDto>moviesSorted =n.getSortedByReleaseDate(movies);
        System.out.println(" The list of movies sorted by releaseDate");
        for( MovieDto movies1 : moviesSorted){
            System.out.println("title"+ movies1.getTitle() + "the releasedate :"+movies1.releaseDate);

        }
        System.out.println("--------------------------------------------------");

        List<MovieDto> highRatedMovies = n.getMoviesByRating(6, movies);
        System.out.println("The list of movies that is higher than 6");
        for (MovieDto movie : highRatedMovies) {
            System.out.println("Title: " + movie.getTitle() + ", Rating: " + movie.getAverage_vote());
        }


    }


    private static List<MovieDto> parseJsonToMovie(String json) {
        List<MovieDto> movies = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        if (jsonObject.has("results")) {
            JsonArray resultsArray = jsonObject.getAsJsonArray("results");

            for (JsonElement element : resultsArray) {
                JsonObject movieObject = element.getAsJsonObject();
                 //converting json file into java opject
                String title = movieObject.get("title").getAsString();
                String overview = movieObject.get("overview").getAsString();
                String releaseDate = movieObject.get("release_date").getAsString();
                double averageVote = movieObject.get("vote_average").getAsDouble();

                MovieDto movieDto = new MovieDto();
                movieDto.setTitle(title);
                movieDto.setOverview(overview);
                movieDto.setReleaseDate(LocalDate.parse(releaseDate));
                movieDto.setAverage_vote(averageVote);

                movies.add(movieDto);
            }
        }

        for (MovieDto movie : movies) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Overview: " + movie.getOverview());
            System.out.println("Release Date: " + movie.getReleaseDate());
            System.out.println("Average Vote: " + movie.getAverage_vote());
            System.out.println();
        }

        return movies;

    }

    private static String geTitle(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")  // Ensure the API returns JSON
                .method("GET", null)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}