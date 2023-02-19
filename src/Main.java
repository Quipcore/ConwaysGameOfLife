import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game gameOfLife = new Game(75,75);
        gameOfLife.run(-1);
    }
}