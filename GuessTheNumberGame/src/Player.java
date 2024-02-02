import java.util.ArrayList;

abstract class Player {
    private String name;
    public int min = 1;
    public int max = 100;
    protected ArrayList<Integer> guesses;

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

    public void setMin(int number){
        this.min = number;
    }
    public void setMax(int number){
        this.max = number;
    }

    public ArrayList<Integer> getGuesses() {
        return guesses;
    }
}
