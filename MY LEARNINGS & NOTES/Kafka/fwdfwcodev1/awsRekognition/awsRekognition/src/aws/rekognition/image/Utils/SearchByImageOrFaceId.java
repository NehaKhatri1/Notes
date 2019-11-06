package aws.rekognition.image.Utils;
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

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchByImageOrFaceId {


	   
	   public static String searchCollectionByImage(String fullImagePath , String collectionId){
	   
       Float similarityThreshold = 70F;
       ByteBuffer sourceImageBytes=null;
       ByteBuffer targetImageBytes=null;
       ByteBuffer intermediateImageBytes=null;
      
       
    
       
       
       BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIASFL2QBMYUXYO2E7F","PwbXC9XsyrObqr6AnGdLHN2Hiw+pb7coxt0uvHa8");

       AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
    		   .withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds))
               .build();
       
     
       
       
       try (InputStream inputStream = new FileInputStream(new File(fullImagePath))) {
           targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }
        catch(Exception e)
        {
            System.out.println("Failed to load target image " + fullImagePath);   
            System.exit(1);
        }
       
       
       Image target=new Image()
       .withBytes(targetImageBytes);
       
     
/**
 * Searching of faces with input being an image 
 */

 ObjectMapper objectMapper = new ObjectMapper();
  SearchFacesByImageRequest searchFacesByImageRequest = new SearchFacesByImageRequest()
        .withCollectionId(collectionId)
        .withImage(target)
        .withFaceMatchThreshold(70F)
        .withMaxFaces(4);
  
  // collection1202 is for collection have group selfie pics 
     
 SearchFacesByImageResult searchFacesByImageResult = 
         rekognitionClient.searchFacesByImage(searchFacesByImageRequest);

// System.out.println("Faces matching largest face in image from the following search reference image : " + target);
 
 String resultForFaceMatch = null;
List < FaceMatch > faceImageMatches = searchFacesByImageResult.getFaceMatches();
for (FaceMatch face: faceImageMatches) {
   System.out.println("************ matched results here ");
	try {
		resultForFaceMatch = (objectMapper.writerWithDefaultPrettyPrinter()
		        .writeValueAsString(face));
//		System.out.println(resultForFaceMatch);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
   }

return resultForFaceMatch;

   }
	   
	   public static void main(String[] args) throws Exception{
		   
		   String fullImagePath = "/home/cloudera/Downloads/ashish/ash1.jpg";
		   String collectionId = "collection1202";
		   
		   searchCollectionByImage(fullImagePath, collectionId);
		   
	   }
 
}