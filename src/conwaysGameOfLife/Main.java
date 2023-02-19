package conwaysGameOfLife;

public class Main {
    public static void main(String[] args) throws Exception {
        Game gameOfLife = new Game(50, 50);
        gameOfLife.run(-1, 100);
    }
}
