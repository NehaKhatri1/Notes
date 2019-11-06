package aws.rekognition.image.Utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

public class CropImage{


	
	
	public static String createImageBasedOnDimensions(Double left , Double top , Double width , Double height , String imageName , String imagePath){
	
	String imageFullNameAndPath = imagePath + imageName;
	Image image = null;
	try {
		image = ImageIO.read(new File(imageFullNameAndPath));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	BufferedImage buffered = (BufferedImage) image;
			
			
int x = (int) Math.abs((buffered.getWidth() * left));// left
int y = (int) Math.abs((buffered.getHeight() * top)); // top
int w = (int) Math.abs((buffered.getWidth() * width)); // width
int h = (int) Math.abs((buffered.getHeight() * height)); // height

int finalX = x + w;
int finalH = y + h;

if (finalX > buffered.getWidth())
    w = buffered.getWidth()-x;

if (finalH > buffered.getHeight())
    h = buffered.getHeight()-y;


System.out.println(finalX);
System.out.println(finalH);

//
//
BufferedImage subImage = buffered.getSubimage(
        x, 
        y, 
        w,
        h);

String outputImageCreatedPath = imagePath + new Date().toString();
outputImageCreatedPath = outputImageCreatedPath.trim();

try {
	ImageIO.write(subImage, "jpg", new File(outputImageCreatedPath+".jpg"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

//
//
//String base64 = ImageUtils.imgToBase64String(subImage, "jpg");

return outputImageCreatedPath+".jpg";


}
	
	public static void main(String[] args) throws IOException{
		
		String imagePath = "/home/cloudera/Downloads/groupSelfie/";
		String imageName = "gr3.jpg";
		Double left =  0.659203;
		Double top =  0.399472;
		Double width =  0.155812;
		Double height =  0.310744;
		
		createImageBasedOnDimensions(left, top, width, height, imageName, imagePath);
		
	
}

}