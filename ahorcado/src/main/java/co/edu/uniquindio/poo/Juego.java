package co.edu.uniquindio.poo;

import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Clase juego que maneja la selección de nivel y el inicio del juego.
 */
public class Juego {
    // Arreglos que contienen las palabras por nivel de dificultad.
    private static final String[] FACIL = {"gato", "perro", "casa", "leon", "loro"};
    private static final String[] MEDIO = {"elefante", "jirafa", "mariposa", "leopardo"};
    private static final String[] DIFICIL = {"hipopotamo", "cocodrilo", "otorrinolaringologo"};

    /**
     * Metodo que permite seleccionar una palabra al azar según el nivel de dificultad.
     * @param nivelDificultad Nivel de dificultad seleccionado.
     * @return Palabra secreta seleccionada.
     */
    private static String seleccionarPalabra(int nivelDificultad) {
        Random random = new Random();
        switch (nivelDificultad) {
            case 1:
                return FACIL[random.nextInt(FACIL.length)];
            case 2:
                return MEDIO[random.nextInt(MEDIO.length)];
            case 3:
                return DIFICIL[random.nextInt(DIFICIL.length)];
            default:
                throw new IllegalArgumentException("Nivel de dificultad no válido");
        }
    }

    /**
     * Método principal del juego.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog(null, "Bienvenido al juego del ahorcado. Por favor, ingresa tu nombre:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return;
        }

        String[] opciones = {"Fácil", "Medio", "Difícil"};
        int nivel = JOptionPane.showOptionDialog(null, "Selecciona el nivel de dificultad:", "Nivel de Dificultad",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]) + 1;

        String palabraSecreta = seleccionarPalabra(nivel);
        Ahorcado juego = new Ahorcado(nombre, palabraSecreta);
        juego.jugar();
    }
}
