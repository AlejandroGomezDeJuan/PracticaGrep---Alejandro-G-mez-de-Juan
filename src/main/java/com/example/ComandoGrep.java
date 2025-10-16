package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ComandoGrep {

    private static final String[] COMANDO = { "grep", "PSP" };
    private static final String TEXTO =
            "Me gusta PSP y java\n" +
            "PSP se programa en java\n" +
            "es un módulo de DAM\n" +
            "y se programa de forma concurrente en PSP\n" +
            "PSP es programación.\n";

    private static final String SALIDA_TITULO = "Salida de grep:";
    private static final String ERROR_GREP = "Error";

    public static void main(String[] args) {
        try {

            Process proceso = Runtime.getRuntime().exec(COMANDO);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            writer.print(TEXTO);
            writer.close();

            System.out.println(SALIDA_TITULO);
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close();

            int exitVal = proceso.waitFor();
            if (exitVal != 0) {
                System.err.println(ERROR_GREP);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
