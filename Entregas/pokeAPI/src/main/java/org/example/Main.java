package org.example;

import org.example.apis.PokeAPICalls;

import java.net.http.HttpRequest;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PokeAPICalls pokeAPICalls = new PokeAPICalls();
        Scanner scanner = new Scanner(System.in);

        // lo ideal seria leer por consola
        System.out.print("Ingrese el nombre del Pokémon: ");
        String nombrePokemon = scanner.nextLine().trim().toLowerCase();

        // de aca queremos nomas su imagen "front_default" y sus habilidades "abilities[]"
        pokeAPICalls.buscarPokemon(nombrePokemon);

        System.out.print("Ingrese el nombre de la habilidad: ");
        String habilidad = scanner.nextLine().trim().toLowerCase();
        // de aca queremos los pokemon que tienen la habilidad "pokemon[]"
        pokeAPICalls.buscarHabilidad(habilidad);


    }
}