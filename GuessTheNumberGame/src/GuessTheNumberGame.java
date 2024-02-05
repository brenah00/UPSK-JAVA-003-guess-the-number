import java.util.Random;
import java.util.Scanner;
import java.io.InputStream;

public class GuessTheNumberGame {
    private int targetNumber;
    private final Scanner scanner;
    public HumanPlayer humanPlayer;
    public ComputerPlayer computerPlayer;

    public static void main(String[] args) {
        System.out.println("BIENVENIDO A GUESS THE NUMBER. ");

        GuessTheNumberGame game = new GuessTheNumberGame();
        game.startGame();
    }
    public GuessTheNumberGame() {
        this(System.in); // Llama al constructor que acepta un InputStream con System.in por defecto
    }

    public GuessTheNumberGame(InputStream inputStream) {
        // Se crea un jugador humano
        humanPlayer = new HumanPlayer();
        System.out.print("Ingresa tu nombre: ");
        humanPlayer.setName(new Scanner(inputStream).nextLine());

        // Se crea jugador computadora
        computerPlayer = new ComputerPlayer();
        computerPlayer.setName("COMPUTADORA");

        // Inicializa el juego y genera un número aleatorio
        setTargetNumber(new Random().nextInt(100) + 1);
        scanner = new Scanner(System.in);

    }

    public void startGame() {
        int round = 1;
        System.out.println("NUMERO GENERADO. COMIENZA EL JUEGO.");
        System.out.println("El número a adivinar está entre 1 y 100.");
        while (!checkGuess(computerPlayer)) {
            System.out.println("************* ROUND "+ round +" ***************");
            // Turno del jugador humano
            humanPlayer.makeGuess();
            System.out.println(displayGuessResult(humanPlayer));
            if (checkGuess(humanPlayer)) {
                break;
            }
            System.out.println("-------------------------------");
            // Turno del jugador de la computadora
            computerPlayer.makeGuess();
            System.out.println(displayGuessResult(computerPlayer));
            round++;
        }
        scanner.close();
    }

    public boolean checkGuess(Player player) {
        int latestGuess = (player.getGuesses().isEmpty()) ? 0 : player.getGuesses().get(player.getGuesses().size() - 1);
        return latestGuess == targetNumber;
    }
    public String displayGuessResult(Player player){
        int latestGuess = (player.getGuesses().isEmpty()) ? 0 : player.getGuesses().get(player.getGuesses().size() - 1);
        if (latestGuess == targetNumber) {
            return player.getName() + " ha adivinado el número. ¡Felicidades!\nTodos tus intentos - "+ player.getGuesses();
        } else if (latestGuess < targetNumber) {
            player.setMin(latestGuess);
            return player.getName() + " ha dicho un número demasiado bajo. Intenta de nuevo.";
        } else {
            player.setMax(latestGuess);
            return player.getName() + " ha dicho un número demasiado alto. Intenta de nuevo.";
        }
    }
    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }
}