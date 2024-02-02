import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
class HumanPlayerTest {
    @Test
    public void testMakeGuessWithValidInput() {
        HumanPlayer player = new HumanPlayer();
        player.setName("Test player");

        String input = "89\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        player.makeGuess(); // Llamamos al método makeGuess que debería leer la entrada simulada;

        assertEquals(89, player.getGuesses().get(0));
        assertEquals("Test player", player.getName());
    }
    @Test
    public void testMakeGuessWithInvalidInputs() {
        String input = "200\n-10\n63\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        HumanPlayer player = new HumanPlayer();
        player.setName("TestPlayer");

        player.makeGuess();

        ArrayList<Integer> guesses = player.getGuesses();
        assertEquals(1, guesses.size());
        assertEquals(63, guesses.get(0));
    }

}