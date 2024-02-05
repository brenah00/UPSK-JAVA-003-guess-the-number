import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class GuessTheNumberGameTest {
    //El usuario adivina el numero

    @Test
    public void testUserAssertsGuess() {
        String playerNameInput = "Brenda\n";
        InputStream inputStream = new ByteArrayInputStream(playerNameInput.getBytes());
        GuessTheNumberGame game = new GuessTheNumberGame(inputStream);
        game.setTargetNumber(69);

        String input = "69\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.humanPlayer.makeGuess();

        assertTrue(game.checkGuess(game.humanPlayer));

        // Comprobar que la salida sea la esperada
        String expectedOutput = "Brenda ha adivinado el número. ¡Felicidades!\nTodos tus intentos - [50]";
        assertEquals(expectedOutput, game.displayGuessResult(game.humanPlayer));
    }

    //El usuario brinda un numero por encima del objetivo
    @Test
    public void testGuessIsHigher() {
        String playerNameInput = "Brenda\n";
        InputStream inputStream = new ByteArrayInputStream(playerNameInput.getBytes());
        GuessTheNumberGame game = new GuessTheNumberGame(inputStream);
        game.setTargetNumber(13);

        String input = "25\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.humanPlayer.makeGuess();

        assertFalse(game.checkGuess(game.humanPlayer));

        // Comprobar que la salida sea la esperada
        String expectedOutput = "Brenda ha dicho un número demasiado alto. Intenta de nuevo.";
        assertEquals(expectedOutput, game.displayGuessResult(game.humanPlayer));
    }

    //El usuario brinda un numero por debajo del objetivo

    @Test
    public void testGuessIsLower() {
        String playerNameInput = "Lina\n";
        InputStream inputStream = new ByteArrayInputStream(playerNameInput.getBytes());
        GuessTheNumberGame game = new GuessTheNumberGame(inputStream);
        game.setTargetNumber(99);

        String input = "110\n10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.humanPlayer.makeGuess();

        assertFalse(game.checkGuess(game.humanPlayer));

        // Comprobar que la salida sea la esperada
        String expectedOutput = "Lina ha dicho un número demasiado bajo. Intenta de nuevo.";
        assertEquals(expectedOutput, game.displayGuessResult(game.humanPlayer));
    }
}
