import java.util.Random;
class ComputerPlayer extends Player {
    @Override
    public void makeGuess() {
        int randomGuess = new Random().nextInt(max - min + 1) + min;
        System.out.println("TURNO DE LA COMPUTADORA. Ha elegido el número: " + randomGuess);
        getGuesses().add(randomGuess);
    }
}