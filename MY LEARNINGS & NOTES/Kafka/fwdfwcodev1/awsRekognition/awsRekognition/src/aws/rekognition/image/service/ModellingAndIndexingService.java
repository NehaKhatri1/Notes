package aws.rekognition.image.service;

import java.util.List;

import aws.rekognition.image.Utils.ModellingAndCollectionCreation;
import aws.rekognition.image.model.BoundingBoxOfFace;

public class ModellingAndIndexingService {
	
	
	
	public static void main(String[]  args){
		
		String pathOfImages = "/home/cloudera/Downloads/index.jpeg;/home/cloudera/Downloads/modi.jpeg";
		
		
		String nameOfCollection = "";
		
		List<BoundingBoxOfFace>  listOfBoundingBoxOfFaces  =ModellingAndCollectionCreation.returnCollectionIdOrBoundariesAfterIndexingFaces(pathOfImages, nameOfCollection);
		
		System.out.println("collection id for persistence is : "+listOfBoundingBoxOfFaces.get(0).getCollectionId());
		
	}

}
