package co.edu.uniquindio.poo;

/**
 * Clase que maneja el dibujo del muñeco del ahorcado.
 */
public class DibujoAhorcado {
    
    // Arreglo que contiene los diferentes estados del dibujo del ahorcado.
    private final String[] dibujo = {
        "  +---+\n      |\n      |\n      |\n     ===",
        "  +---+\n  O   |\n      |\n      |\n     ===",
        "  +---+\n  O   |\n  |   |\n      |\n     ===",
        "  +---+\n  O   |\n /|   |\n      |\n     ===",
        "  +---+\n  O   |\n /|\\  |\n      |\n     ===",
        "  +---+\n  O   |\n /|\\  |\n /    |\n     ===",
        "  +---+\n  O   |\n /|\\  |\n / \\  |\n     ==="
    };

    /**
     * Devuelve el dibujo del ahorcado correspondiente al número de vidas restantes.
     * @param vidas Número de vidas restantes.
     * @return Dibujo del ahorcado.
     */
    public String dibujar(int vidas) {
        return dibujo[6 - vidas];
    }
}
