package co.edu.uniquindio.poo;

import java.util.Random;

public class Jugador {
    private boolean esMaquina;

    public Jugador(boolean esMaquina) {
        this.esMaquina = esMaquina;
    }

    public boolean esMaquina() {
        return esMaquina;
    }

    public int[] obtenerMovimiento(Tablero tablero) {
        if (esMaquina) {
            return obtenerMovimientoAleatorio(tablero);
        } else {
            return null; // El jugador humano ahora interactúa con la interfaz gráfica.
        }
    }

    private int[] obtenerMovimientoAleatorio(Tablero tablero) {
        Random rand = new Random();
        int fila, columna;
        do {
            fila = rand.nextInt(3);
            columna = rand.nextInt(3);
        } while (tablero.esPosicionOcupada(fila, columna));
        return new int[]{fila, columna};
    }
}