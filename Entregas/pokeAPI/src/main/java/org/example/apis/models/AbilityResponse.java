package org.example.apis.models;

import org.example.apis.models.Ability;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityResponse {
    @JsonProperty("pokemon")
    private List<AbilityPokemon> pokemon;

    // Getter and setter

    public List<AbilityPokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<AbilityPokemon> pokemon) {
        this.pokemon = pokemon;
    }
}