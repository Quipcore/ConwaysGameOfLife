import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cell {

    private boolean isAlive;

    private BufferedImage aliveImage;
    private BufferedImage deadImage;

    public final int IMAGE_WIDTH = 10;
    public final int IMAGE_HEIGHT = 10;


    public Cell(boolean isAlive) throws IOException {
        setStatus(isAlive);

        aliveImage = ImageIO.read(new File("src/resources/assets/images/alive.png"));
        deadImage = ImageIO.read(new File("src/resources/assets/images/dead.png"));

    }

    boolean isAlive(){
        return isAlive;
    }

    private void kill(){
        isAlive = false;
    }

    private void resurrect(){
        isAlive = true;
    }

    public void setStatus(boolean status){
        this.isAlive = status;
    }

    public BufferedImage getAliveImage(){
        return aliveImage;
    }

    public BufferedImage getDeadImage(){
        return deadImage;
    }

    public BufferedImage getStatusImage() {
        return isAlive ? getAliveImage() : getDeadImage();
    }
}
