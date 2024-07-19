package co.edu.uniquindio.poo;

import javax.swing.JOptionPane;

/**
 * Clase que maneja la lógica del juego del ahorcado.
 */
public class Ahorcado {
    private String palabraSecreta;
    private StringBuilder palabraAdivinada;
    private int vidas;
    private String nombreJugador;
    private DibujoAhorcado dibujoAhorcado;

    /**
     * Constructor que inicializa el juego
     * @param nombreJugador
     * @param palabraSecreta
     */
    public Ahorcado(String nombreJugador, String palabraSecreta) {
        this.nombreJugador = nombreJugador;
        this.vidas = 6;
        this.palabraSecreta = palabraSecreta;
        this.palabraAdivinada = new StringBuilder("_".repeat(palabraSecreta.length()));
        this.dibujoAhorcado = new DibujoAhorcado();
    }

    /**
     * Método principal del juego, que controla el flujo de juego.
     */
    public void jugar() {
        // Bucle principal del juego.
        while (vidas > 0 && palabraAdivinada.indexOf("_") >= 0) {
            // Solicita al jugador una letra
            String letra = JOptionPane.showInputDialog(null, "Palabra: " + palabraAdivinada + "\n" + dibujoAhorcado.dibujar(vidas) + "\nVidas restantes: " + vidas + "\nAdivina una letra:");
            if (letra == null || letra.length() != 1) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una sola letra.");
                continue;
            }
            // Verifica la letra ingresada.
            verificarLetra(letra.charAt(0));
        }
        
        // Mensaje final del juego.
        if (palabraAdivinada.indexOf("_") < 0) {
            JOptionPane.showMessageDialog(null, "¡Felicidades " + nombreJugador + "! Has adivinado la palabra secreta: " + palabraSecreta);
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento " + nombreJugador + ". Has perdido. La palabra era: " + palabraSecreta);
        }
    }

    /**
     * Verifica si la letra ingresada está en la palabra secreta.
     * @param letra
     */
    private void verificarLetra(char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraAdivinada.setCharAt(i, letra);
                acierto = true;
            }
        }

        // Si no acertó, se resta una vida.
        if (!acierto) {
            vidas--;
        }
    }
}
