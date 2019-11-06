package aws.rekognition.image.Utils;
import aws.rekognition.image.model.BoundingBoxOfFace;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;
import com.amazonaws.services.rekognition.model.Face;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.FaceMatch;
import com.amazonaws.services.rekognition.model.FaceRecord;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.IndexFacesRequest;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
import com.amazonaws.services.rekognition.model.ListCollectionsRequest;
import com.amazonaws.services.rekognition.model.ListCollectionsResult;
import com.amazonaws.services.rekognition.model.ListFacesRequest;
import com.amazonaws.services.rekognition.model.ListFacesResult;
import com.amazonaws.services.rekognition.model.QualityFilter;
import com.amazonaws.services.rekognition.model.SearchFacesByImageRequest;
import com.amazonaws.services.rekognition.model.SearchFacesByImageResult;
import com.amazonaws.services.rekognition.model.SearchFacesRequest;
import com.amazonaws.services.rekognition.model.SearchFacesResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.JSONInput;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModellingAndCollectionCreation {
	
	
	   
	// the method has to return collection id in case of all images indexed , else it has to return list of boundaries separated by ; in case of reference image being multi faced.
	   public static List<BoundingBoxOfFace> returnCollectionIdOrBoundariesAfterIndexingFaces (String pathsOfImages , String nameOfCollection){
	   
       ByteBuffer sourceImageBytes=null;
       ByteBuffer intermediateImageBytes=null;
       List<BoundingBoxOfFace> listOfBoundingBoxesOfFace = null;
       List<BoundingBoxOfFace> finalListOfBoundingBoxesOfFace = null;
      
       BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIASFL2QBMYUXYO2E7F","PwbXC9XsyrObqr6AnGdLHN2Hiw+pb7coxt0uvHa8");

       AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
    		   .withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds))
               .build();
       
       Integer randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
       String collectionId = "collection"+randomNum.toString();
       
       
       CreateCollectionRequest request = new CreateCollectionRequest()
       .withCollectionId(collectionId);
System.out.println("creating collection id");
System.out.println(request);

CreateCollectionResult createCollectionResult = rekognitionClient.createCollection(request); 
System.out.println("CollectionArn : " +
createCollectionResult.getCollectionArn());
       
       
       
       String[] arrayOfImages  = pathsOfImages.split(";");
       
       
       for(int i=0;i<arrayOfImages.length;i++){
       
       try (InputStream inputStream = new FileInputStream(new File(arrayOfImages[i]))) {
    	   intermediateImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }
        catch(Exception e)
        {
            System.out.println("Failed to load target image " + arrayOfImages[i]);   
            System.exit(1);
        }
       
     
      
       Image intermediate=new Image()
       .withBytes(intermediateImageBytes);
       


IndexFacesRequest indexFacesRequestIntermediate = new IndexFacesRequest()
.withImage(intermediate)
.withQualityFilter(QualityFilter.AUTO)
.withMaxFaces(2)
.withCollectionId(collectionId)
.withExternalImageId("nameOfTheImage")
.withDetectionAttributes("DEFAULT");


 rekognitionClient.indexFaces(indexFacesRequestIntermediate);

ListFacesRequest listFacesRequest = new ListFacesRequest()
.withCollectionId(collectionId);

ObjectMapper objectMapper = new ObjectMapper();

ListFacesResult listFacesResult =  rekognitionClient.listFaces(listFacesRequest);
List < Face > faces = listFacesResult.getFaces();
String valueForMultiFaceInSingleImage = "";
listOfBoundingBoxesOfFace = new LinkedList<BoundingBoxOfFace>();
for (Face face: faces) {
	System.out.println("*** listing of faces here *****");
   try {
	   valueForMultiFaceInSingleImage = (objectMapper.writerWithDefaultPrettyPrinter()
	      .writeValueAsString(face));
	   JSONObject js = new JSONObject(valueForMultiFaceInSingleImage);
	   JSONObject boundinBoxOfFace = (JSONObject) js.get("boundingBox");
	   Double width = (Double)boundinBoxOfFace.get("width");
//	   System.out.println("width:"+width);
	   Double height = (Double)boundinBoxOfFace.get("height");
	   Double left = (Double)boundinBoxOfFace.get("left");
	   Double top = (Double)boundinBoxOfFace.get("top");
	   
	   BoundingBoxOfFace boundingBoxOfFaceModel = new BoundingBoxOfFace();
	   boundingBoxOfFaceModel.setWidth(width);
	   boundingBoxOfFaceModel.setHeight(height);
	   boundingBoxOfFaceModel.setLeft(left);
	   boundingBoxOfFaceModel.setTop(top);
	   
	   listOfBoundingBoxesOfFace.add(boundingBoxOfFaceModel);
	   
	   
	   System.out.println(valueForMultiFaceInSingleImage);
} catch (JsonProcessingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
} 

if(faces.size()>1&&arrayOfImages.length==1){
	finalListOfBoundingBoxesOfFace = listOfBoundingBoxesOfFace;
}
else{
	BoundingBoxOfFace boundingBoxOfFaceModel = new BoundingBoxOfFace();
	boundingBoxOfFaceModel.setCollectionId(collectionId);
	finalListOfBoundingBoxesOfFace = new LinkedList<BoundingBoxOfFace>();
	finalListOfBoundingBoxesOfFace.add(boundingBoxOfFaceModel);
}

       } // end of for loop
       

	return finalListOfBoundingBoxesOfFace;

   }
	   
	   public static void main(String[] args) throws Exception{
		   String nameOfCollection = "collection";
		   String pathsOfImages = "/home/cloudera/Downloads/groupSelfie/gr3.jpg"; ///home/cloudera/Downloads/index.jpeg;/home/cloudera/Downloads/index.jpeg;
		   List<BoundingBoxOfFace> listOfReturnedBoundaryBoxes = returnCollectionIdOrBoundariesAfterIndexingFaces(pathsOfImages, nameOfCollection);
		   System.out.println("###############################returnedValueFromIndexingMethod################################### \n ");
		   if(listOfReturnedBoundaryBoxes.size()==1){
			   System.out.println("collection id in case of indexing of multiple images is  : ");
		   System.out.println(""+listOfReturnedBoundaryBoxes.get(0).getCollectionId());
		   }
		   else{
			   for(int i=0;i<listOfReturnedBoundaryBoxes.size();i++){
				   BoundingBoxOfFace boundingBoxOfFace = listOfReturnedBoundaryBoxes.get(i);
				   System.out.println("***dimensions of faces  in case of        reference search image being indexed are  : ***");
				   System.out.println(boundingBoxOfFace.getWidth());
				   System.out.println(boundingBoxOfFace.getHeight());
			   }
		   }
	   
	   }
 
}