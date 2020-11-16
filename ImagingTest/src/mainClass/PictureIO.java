package mainClass;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class PictureIO {
	//∂¡»°Õº∆¨
    public static BufferedImage getGoalImage(String imagePath) throws Exception{
        File file = new File(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        }
        catch (Exception e){
            image = null;
        }
        return image;
    }
    //–¥»ÎÕº∆¨
    public static boolean writeGoalImage(BufferedImage newImage, String Path, String newPictureName)
            throws Exception{
        boolean result = false;
        File outputfile = new File(Path + newPictureName + ".jpg" );
        try {
            ImageIO.write(newImage, "jpg", outputfile);
            result = true;
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }
}
