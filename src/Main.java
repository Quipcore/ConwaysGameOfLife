import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game gameOfLife = new Game(50,50);
        gameOfLife.run(100);
    }
}