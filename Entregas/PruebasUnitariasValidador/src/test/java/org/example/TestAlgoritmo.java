package org.example;
import org.example.Algoritmo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAlgoritmo {


    @Test
    public void rechazaContraseniaMenor8Caracteres()
    {
        boolean resultado1 = Algoritmo.is_password_too_short("abcdefg");
        assertTrue(resultado1);
    }

    @Test
    public void aceptaContraseniaDe8Caracteres()
    {
        boolean resultado1 = Algoritmo.is_password_too_short("abcdefgh");
        assertFalse(resultado1);
    }

    @Test
    public void aceptaContraseniaMas8Caracteres()
    {
        boolean resultado = Algoritmo.is_password_too_short("abcdefghe");
        assertFalse(resultado);
    }

    @Test
    public void contraseñaPresenteTop10000() throws IOException {
        boolean resultado1 = Algoritmo.is_password_in_top_1000("12345678");
        assertTrue(resultado1);
    }

    @Test
    public void contraseñaNoPresenteTop10000() throws IOException {
        boolean resultado1 = Algoritmo.is_password_in_top_1000("holaComoEstas");
        assertFalse(resultado1);
    }

    @Test
    public void rechazaContraseniaVacia()
    {
        boolean resultado1 = Algoritmo.is_password_too_short("");
        assertTrue(resultado1);
    }

    @Test
    public void contraseniaVaciaNoPresenteTop10000() throws IOException {
        boolean resultado1 = Algoritmo.is_password_in_top_1000("");
        assertFalse(resultado1);
    }

    @Test
    public void rechazaContraseniaPresenteTop10000PeroDistintoFormato() throws IOException
    {
        boolean resultado1 = Algoritmo.is_password_in_top_1000("PASSWORD");
        boolean resultado2 = Algoritmo.is_password_in_top_1000("PaSSwORd");
        assertTrue(resultado1);
        assertTrue(resultado2);
    }
}
