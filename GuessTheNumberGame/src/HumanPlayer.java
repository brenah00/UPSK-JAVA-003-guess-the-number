import java.util.Scanner;

public class HumanPlayer extends Player {
    public void makeGuess() {
        Scanner scanner = new Scanner(System.in); // Crear el Scanner una vez fuera del bucle
        System.out.print("TURNO DE " + getName() + ". Ingresa un número: ");
        int inputNumber = 10;
        do {
            if (inputNumber > 100 || inputNumber < 1) {
                System.out.print("TURNO DE " + getName() + ". Ingresa un número válido: ");
            }
            inputNumber = Integer.parseInt(scanner.nextLine());
        } while (inputNumber > 100 || inputNumber < 1);

        getGuesses().add(inputNumber);
    }
}