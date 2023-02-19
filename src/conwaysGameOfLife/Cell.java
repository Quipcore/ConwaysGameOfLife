package conwaysGameOfLife;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cell {

    private final BufferedImage aliveImage;
    private final BufferedImage deadImage;

    private final int IMAGE_WIDTH;
    private final int IMAGE_HEIGHT;

    private boolean isAlive;

    //---------------------------------------------------------------------------------

    public Cell(boolean isAlive) throws Exception {
        setStatus(isAlive);

        aliveImage = ImageIO.read(new File("src/resources/assets/images/alive.png"));
        deadImage = ImageIO.read(new File("src/resources/assets/images/dead.png"));

        if (aliveImage.getWidth() != deadImage.getWidth() || aliveImage.getHeight() != deadImage.getHeight()) {
            throw new Exception("Images not same size");
        }

        IMAGE_WIDTH = aliveImage.getWidth();
        IMAGE_HEIGHT = aliveImage.getHeight();
    }

    //---------------------------------------------------------------------------------

    boolean isAlive() {
        return isAlive;
    }

    //---------------------------------------------------------------------------------

    public void setStatus(boolean status) {
        this.isAlive = status;
    }

    //---------------------------------------------------------------------------------

    public BufferedImage getAliveImage() {
        return aliveImage;
    }

    //---------------------------------------------------------------------------------

    public BufferedImage getDeadImage() {
        return deadImage;
    }

    //---------------------------------------------------------------------------------

    public BufferedImage getStatusImage() {
        return isAlive ? getAliveImage() : getDeadImage();
    }

    //---------------------------------------------------------------------------------

    public int getIMAGE_WIDTH() {
        return IMAGE_WIDTH;
    }

    //---------------------------------------------------------------------------------

    public int getIMAGE_HEIGHT() {
        return IMAGE_HEIGHT;
    }
}
