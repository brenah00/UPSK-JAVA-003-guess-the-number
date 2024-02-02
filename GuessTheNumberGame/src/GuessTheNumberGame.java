import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private int targetNumber;
    private Scanner scanner;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;

    public static void main(String[] args) {
        System.out.println("BIENVENIDO A GUESS THE NUMBER. ");

        GuessTheNumberGame game = new GuessTheNumberGame();
        game.startGame();
    }

    public GuessTheNumberGame() {
        // Crea un jugador humano
        humanPlayer = new HumanPlayer();
        System.out.print("Ingresa tu nombre: ");
        humanPlayer.setName(new Scanner(System.in).nextLine());

        // Crea un jugador de la computadora
        computerPlayer = new ComputerPlayer();
        computerPlayer.setName("Computer");

        // Inicializa el juego y genera un número aleatorio
        targetNumber = new Random().nextInt(100) + 1;
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
            if (checkGuess(humanPlayer)) {
                break;
            }
            System.out.println("-------------------------------");
            // Turno del jugador de la computadora
            computerPlayer.makeGuess();
            round++;
        }
        scanner.close();
    }

    private boolean checkGuess(Player player) {
        int latestGuess = (player.getGuesses().isEmpty()) ? 0 : player.getGuesses().get(player.getGuesses().size() - 1);
        if(latestGuess == 0){ return false; }
        if (latestGuess == targetNumber) {
            System.out.println(player.getName() + " ha adivinado el número. ¡Felicidades!\nTodos tus intentos - "+ player.getGuesses());
            return true;
        } else if (latestGuess < targetNumber) {
            System.out.println(player.getName() + " ha dicho un número demasiado bajo. Intenta de nuevo.");
            player.setMin(latestGuess);
        } else {
            System.out.println(player.getName() + " ha dicho un número demasiado alto. Intenta de nuevo.");
            player.setMax(latestGuess);
        }
        return false;
    }
}