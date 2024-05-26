package org.example.apis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NamedAPIResource {
    @JsonProperty("name")
    private String name;

    // Getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}