package org.example.apis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityPokemon {
    @JsonProperty("pokemon")
    private NamedAPIResource pokemon;

    // Getter and setter

    public NamedAPIResource getPokemon() {
        return pokemon;
    }

    public void setPokemon(NamedAPIResource pokemon) {
        this.pokemon = pokemon;
    }
}