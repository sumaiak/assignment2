package org.example.Threads;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercise6 {
    public static void main(String[] args) {
        String[] urls = new String[]{
                "https://icanhazdadjoke.com/",
                "https://api.chucknorris.io/jokes/random",
                "https://restcountries.com/v3.1/name/India?fullText=true",
                "https://api.agify.io?name=michael",
                "https://pokeapi.co/api/v2/pokemon/ditto"
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> futureResults = new ArrayList<>();

        for (String url : urls) {
            Future<String> future = executor.submit(() -> gettingHttpResAndReq(url));
            futureResults.add(future);
        }

        PokemonDTO pokemonDTO = null;
        AgifyDTO agifyDTO = null;
        CountryDTO countryDTO = null;
        ChuckNorrisJokeDTO chuckNorrisJokeDTO = null;
        DadJokeDTO dadJokeDTO = null;

        for (int i = 0; i < urls.length; i++) {
            try {
                String response = futureResults.get(i).get();
                switch (i) {
                    case 0:
                        dadJokeDTO = parseDTO(response, DadJokeDTO.class);
                        break;
                    case 1:
                        chuckNorrisJokeDTO = parseDTO(response, ChuckNorrisJokeDTO.class);
                        break;
                    case 2:
                        countryDTO = parseDTO(response, CountryDTO.class);
                        break;
                    case 3:
                        agifyDTO = parseDTO(response, AgifyDTO.class);
                        break;
                    case 4:
                        pokemonDTO = parseDTO(response, PokemonDTO.class);
                        break;
                    default:
                        break;
                }
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        CapsulateAllDtos capsulateAllDtos = new CapsulateAllDtos(pokemonDTO, agifyDTO, countryDTO, chuckNorrisJokeDTO, dadJokeDTO);
        capsulateAllDtos.printAllDtos();

        executor.shutdown();
    }

    private static String gettingHttpResAndReq(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")  // Ensure the API returns JSON
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error fetching data from " + url + ": " + e.getMessage());
        }
    }

    // Generic method to parse JSON into DTO
    public static <T> T parseDTO(String json, Class<T> clazz) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Error parsing JSON into DTO: " + e.getMessage());
        }
    }

    @Data
    public static class DadJokeDTO {
        private String id;
        private String joke;
    }

    @Data
    public static class ChuckNorrisJokeDTO {
        private String id;
        private String value;
    }

    @Data
    public static class CountryDTO {
        private String name;
        private String capital;
        private String region;
    }

    @Data
    public static class PokemonDTO {
        private int id;
        private String name;
        private int height;
        private int weight;
    }

    @Data
    public static class AgifyDTO {
        private String name;
        private int age;
        private int count;
        private String country_id;
    }

    // Class to encapsulate all DTOs
    public static class CapsulateAllDtos {
        PokemonDTO poki;
        AgifyDTO agify;
        CountryDTO country;
        ChuckNorrisJokeDTO norris;
        DadJokeDTO joke;

        public CapsulateAllDtos(PokemonDTO poki, AgifyDTO agify, CountryDTO country, ChuckNorrisJokeDTO norris, DadJokeDTO joke) {
            this.poki = poki;
            this.agify = agify;
            this.country = country;
            this.norris = norris;
            this.joke = joke;
        }

        public void printAllDtos() {
            System.out.println("Pokemon DTO: " + poki);
            System.out.println("Agify DTO: " + agify);
            System.out.println("Country DTO: " + country);
            System.out.println("Chuck Norris Joke DTO: " + norris);
            System.out.println("Dad Joke DTO: " + joke);
        }
    }
}
