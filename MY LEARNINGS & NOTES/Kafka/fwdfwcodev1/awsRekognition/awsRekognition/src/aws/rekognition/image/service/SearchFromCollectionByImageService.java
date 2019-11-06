package aws.rekognition.image.service;

import java.util.List;

import aws.rekognition.image.Utils.CropImage;
import aws.rekognition.image.Utils.ModellingAndCollectionCreation;
import aws.rekognition.image.Utils.SearchByImageOrFaceId;
import aws.rekognition.image.model.BoundingBoxOfFace;

public class SearchFromCollectionByImageService {
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 * 
	 * collection1202 : for images of aparnesh and ashish
	 * collection46664 : for images of aparnesh and modi
	 * collection68521 : for two images of modi
	 * collection49479 : for images of ashish and modi
	 */
	
	
	
	  public static void main(String[] args) throws Exception{
		   String collectionFromWhereToBeSearched = "collection49479";
		   String nameOfCollection = "collection";
		   String imagePath = "/home/cloudera/Downloads/groupSelfie/";
		   String imageName = "gr3.jpg;";
		   String pathsOfImages = imagePath+imageName; ///home/cloudera/Downloads/index.jpeg;/home/cloudera/Downloads/index.jpeg;
		   List<BoundingBoxOfFace> listOfReturnedBoundaryBoxes = ModellingAndCollectionCreation.returnCollectionIdOrBoundariesAfterIndexingFaces(pathsOfImages, nameOfCollection);
		   
		   System.out.println("###############################returnedValueFromIndexingMethod################################### \n ");
		   
		   if(listOfReturnedBoundaryBoxes.size()==1){
//			   System.out.println("collection id in case of indexing of multiple images is  : ");
		   System.out.println(""+listOfReturnedBoundaryBoxes.get(0).getCollectionId());
		   String searchDetails = SearchByImageOrFaceId.searchCollectionByImage(pathsOfImages.substring(0, pathsOfImages.length()-1), collectionFromWhereToBeSearched);
		   System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$     CASE OF REFERENCE IMAGE BEING SINGLE FACED     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		   System.out.println("search details are : "+searchDetails);
		   }
		   else{
			   for(int i=0;i<listOfReturnedBoundaryBoxes.size();i++){
				   
				   BoundingBoxOfFace boundingBoxOfFace = listOfReturnedBoundaryBoxes.get(i);
//				   System.out.println("***dimensions of faces  in case of reference search image being indexed are  : ***");
				   
					
					String createdImagePath = CropImage.createImageBasedOnDimensions(boundingBoxOfFace.getLeft(), boundingBoxOfFace.getTop(), boundingBoxOfFace.getWidth(), boundingBoxOfFace.getHeight(), imageName.substring(0, imageName.length()-1), imagePath);
					 String searchDetails = SearchByImageOrFaceId.searchCollectionByImage(createdImagePath.substring(0, createdImagePath.length()), collectionFromWhereToBeSearched);
					 System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$     CASE OF REFERENCE IMAGE BEING MULTI FACED     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					   System.out.println("search details are : "+searchDetails);
					
			   }
		   }
	   
	   }

}
