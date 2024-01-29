import java.util.ArrayList;
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
        // Crear un jugador humano
        humanPlayer = new HumanPlayer();
        System.out.print("Ingresa tu nombre: ");
        humanPlayer.setName(new Scanner(System.in).nextLine());

        // Crear un jugador de la computadora
        computerPlayer = new ComputerPlayer();

        // Inicializar el juego y generar un número aleatorio
        targetNumber = new Random().nextInt(100) + 1;
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        int round = 1;
        System.out.println("NUMERO GENERADO. COMIENZA EL JUEGO.");
        System.out.println("El número a adivinar está entre 1 y 100.");
        while (!checkGuess(humanPlayer) && !checkGuess(computerPlayer)) {
            System.out.println("************* ROUND "+ round +" ***************");
            // Turno del jugador humano
            humanPlayer.makeGuess();
            displayGuessResults(humanPlayer);
            if (checkGuess(humanPlayer)) {
                break; // Si el jugador humano adivina, salimos del bucle
            }
            System.out.println("-------------------------------");
            // Turno del jugador de la computadora
            computerPlayer.makeGuess();
            displayGuessResults(computerPlayer);
            round++;
        }

        scanner.close();
    }


    private boolean checkGuess(Player player) {
        int latestGuess = (player.getGuesses().isEmpty()) ? 0 : player.getGuesses().get(player.getGuesses().size() - 1);
        return latestGuess == targetNumber;
    }

    private void displayGuessResults(Player player) {
        int latestGuess = player.getGuesses().get(player.getGuesses().size() - 1);
        if (latestGuess == targetNumber) {
            System.out.println(player.getName() + " ha adivinado el número. ¡Felicidades!");
        } else if (latestGuess < targetNumber) {
            System.out.println(player.getName() + " ha dicho un número demasiado bajo. Intenta de nuevo.");
        } else {
            System.out.println(player.getName() + " ha dicho un número demasiado alto. Intenta de nuevo.");
        }
    }

    abstract static class Player {
        private String name;
        private ArrayList<Integer> guesses;

        public Player() {
            this.guesses = new ArrayList<>();
        }

        public abstract void makeGuess();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Integer> getGuesses() {
            return guesses;
        }
    }

    static class HumanPlayer extends Player {
        public void makeGuess() {
            System.out.print("TURNO DE " + getName() + ". Ingresa un número: ");
            int inputNumber = Integer.parseInt(new Scanner(System.in).nextLine());
            getGuesses().add(inputNumber);
        }
    }

    static class ComputerPlayer extends Player {
        @Override
        public void makeGuess() {
            int randomGuess = new Random().nextInt(100) + 1;
            System.out.println("TURNO DE LA COMPUTADORA. Ha elegido el número: " + randomGuess);
            getGuesses().add(randomGuess);
        }
    }
}
