package co.edu.uniquindio.poo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Juego juego = new Juego();
            juego.mostrarMenuPrincipal();
        });
    }
}