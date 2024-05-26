package org.example.apis;

import org.apache.cxf.jaxrs.client.WebClient;

import org.example.apis.models.*;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInput;

public class PokeAPICalls {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void buscarPokemon(String nombrePokemon){
        WebClient client = WebClient.create(BASE_URL);

        Response response = client.path("pokemon/" + nombrePokemon)
                .accept(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200){
            try {

                String responseBody = response.readEntity(String.class);
                Pokemon pokemon = objectMapper.readValue(responseBody, Pokemon.class);

                // Obtener imagen del pokemon
                String imageUrl = pokemon.getSprites().getFrontDefault();
                System.out.println("URL de imagen: " + imageUrl);

                // Obtener habilidades del pokemon
                for(AbilityWrapper abilityWrapper : pokemon.getAbilities()) {
                    String abilityName = abilityWrapper.getAbility().getName();
                    System.out.println("Habilidad de " + pokemon.getName() + ": " + abilityName);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Fallo en obtener informacion del pokemon, se obtuvo el status " + response.getStatus());
        }

    }

    public static void buscarHabilidad(String abilityName) {
        WebClient client = WebClient.create(BASE_URL);

        Response response = client.path("ability/" + abilityName)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {
            try {
                String responseBody = response.readEntity(String.class);
                AbilityResponse abilityResponse = objectMapper.readValue(responseBody, AbilityResponse.class);

                // Obtener los Pokémon con la habilidad
                for (AbilityPokemon abilityPokemon : abilityResponse.getPokemon()) {
                    String pokemonName = abilityPokemon.getPokemon().getName();
                    System.out.println("Pokemon con habilidad " + abilityName + ": "+ pokemonName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Fallo en obtener info de habilidades, se obtuvo el status " + response.getStatus());
        }
    }
}
