import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cell {

    private boolean isAlive;

    private boolean previousStatus;
    private BufferedImage aliveImage;
    private BufferedImage deadImage;

    public final int IMAGE_WIDTH = 10;
    public final int IMAGE_HEIGHT = 10;




    public Cell(boolean isAlive) throws IOException {
        setStatus(isAlive);
        setPreviousStatus(isAlive);
        aliveImage = ImageIO.read(new File("src/resources/assets/images/alive.png"));
        deadImage = ImageIO.read(new File("src/resources/assets/images/dead.png"));

    }

    void setPreviousStatus(boolean isAlive) {
        this.previousStatus = isAlive;
    }

    public boolean getPreviousStatus(){
        return previousStatus;
    }


    boolean isAlive(){
        return isAlive;
    }

    private void kill() {
        setPreviousStatus(isAlive);
        isAlive = false;
    }

    private void resurrect(){
        setPreviousStatus(isAlive);
        isAlive = true;
    }

    public void setStatus(boolean status){
        setPreviousStatus(isAlive);
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
