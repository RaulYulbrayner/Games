package co.edu.uniquindio.poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero extends JPanel {
    JButton[][] botones;
    private char[][] tablero;
    private char jugadorActual;

    public Tablero() {
        tablero = new char[3][3];
        botones = new JButton[3][3];
        jugadorActual = 'X';
        inicializarTablero();
        inicializarBotones();
        this.setLayout(new GridLayout(3, 3));
        agregarBotones();
    }

    private void inicializarBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                int finalI = i;
                int finalJ = j;
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (tablero[finalI][finalJ] == '-') {
                            colocarMarca(finalI, finalJ);
                            botones[finalI][finalJ].setText(String.valueOf(jugadorActual));
                            if (hayGanador()) {
                                JOptionPane.showMessageDialog(null, "¡El jugador " + jugadorActual + " gana!");
                                reiniciarTablero();
                                firePropertyChange("gameOver", false, true);
                            } else if (estaLleno()) {
                                JOptionPane.showMessageDialog(null, "¡Es un empate!");
                                reiniciarTablero();
                                firePropertyChange("gameOver", false, true);
                            } else {
                                cambiarJugador();
                                firePropertyChange("nextTurn", false, true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Posición ya ocupada. Intente nuevamente.");
                        }
                    }
                });
            }
        }
    }

    private void agregarBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.add(botones[i][j]);
            }
        }
    }

    public void reiniciarTablero() {
        inicializarTablero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
            }
        }
        jugadorActual = 'X';
    }

    public void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    public boolean estaLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hayGanador() {
        return (revisarFilas() || revisarColumnas() || revisarDiagonales());
    }

    private boolean revisarFilas() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][0] != '-') {
                return true;
            }
        }
        return false;
    }

    private boolean revisarColumnas() {
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i] && tablero[0][i] != '-') {
                return true;
            }
        }
        return false;
    }

    private boolean revisarDiagonales() {
        return ((tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != '-') ||
                (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != '-'));
    }

    public void colocarMarca(int fila, int columna) {
        if (fila >= 0 && columna >= 0 && fila < 3 && columna < 3 && tablero[fila][columna] == '-') {
            tablero[fila][columna] = jugadorActual;
        }
    }

    public void cambiarJugador() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    public char obtenerJugadorActual() {
        return jugadorActual;
    }

    public boolean esPosicionOcupada(int fila, int columna) {
        return tablero[fila][columna] != '-';
    }
}