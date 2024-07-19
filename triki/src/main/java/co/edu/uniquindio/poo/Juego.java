package co.edu.uniquindio.poo;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Juego {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private JFrame frame;

    public Juego() {
        tablero = new Tablero();
        frame = new JFrame("Triki");
        frame.setSize(400, 400);
        frame.add(tablero);

        tablero.addPropertyChangeListener("nextTurn", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (jugador2.esMaquina() && tablero.obtenerJugadorActual() == 'O') {
                    int[] movimiento = jugador2.obtenerMovimiento(tablero);
                    if (movimiento != null) {
                        tablero.colocarMarca(movimiento[0], movimiento[1]);
                        tablero.botones[movimiento[0]][movimiento[1]].setText(String.valueOf(tablero.obtenerJugadorActual()));
                        if (tablero.hayGanador()) {
                            JOptionPane.showMessageDialog(null, "¡El jugador " + tablero.obtenerJugadorActual() + " gana!");
                            reiniciarJuego();
                        } else if (tablero.estaLleno()) {
                            JOptionPane.showMessageDialog(null, "¡Es un empate!");
                            reiniciarJuego();
                        } else {
                            tablero.cambiarJugador();
                        }
                    }
                }
            }
        });

        tablero.addPropertyChangeListener("gameOver", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                reiniciarJuego();
            }
        });
    }

    public void mostrarMenuPrincipal() {
        String[] opciones = {"Jugar vs Máquina", "Dos Jugadores"};
        int eleccion = JOptionPane.showOptionDialog(null, "Seleccione el modo de juego", "Triki",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        switch (eleccion) {
            case 0:
                jugarContraMaquina();
                break;
            case 1:
                dosJugadores();
                break;
            default:
                System.exit(0);
        }
    }

    public void jugarContraMaquina() {
        jugador1 = new Jugador(false); // Humano
        jugador2 = new Jugador(true);  // Máquina
        iniciarJuego();
    }

    public void dosJugadores() {
        jugador1 = new Jugador(false);
        jugador2 = new Jugador(false);
        iniciarJuego();
    }

    private void iniciarJuego() {
        tablero.reiniciarTablero();
        frame.setVisible(true);
    }

    private void reiniciarJuego() {
        frame.setVisible(false);
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres jugar otra vez?", "Juego Terminado", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            mostrarMenuPrincipal();
        } else {
            System.exit(0);
        }
    }
}