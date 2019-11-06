package aws.rekognition.image.Utils;
/*package aws.rekognition.image;
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

public class ModellingAndCollectionCreation {

	
	
 
	   
	// the method has to return collection id in case of all images indexed , else it has to return list of boundaries separated by ; in case of reference image being multi faced.
	   public static String returnCollectionIdOrBoundariesAfterIndexingFaces (String pathsOfImages , String nameOfCollection){
	   
	  
       Float similarityThreshold = 70F;
     
       ByteBuffer sourceImageBytes=null;
       ByteBuffer intermediateImageBytes=null;
      
       BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIASFL2QBMYUXYO2E7F","PwbXC9XsyrObqr6AnGdLHN2Hiw+pb7coxt0uvHa8");

       AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
    		   .withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds))
               .build();
       
       
     
       String sourceImage1 = "/home/cloudera/Downloads/groupSelfie/gr3.jpg";
       String intermediateImage = "/home/cloudera/Downloads/groupSelfie/gr3.jpg";
       
       
       Integer randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
       String collectionId = "collection"+randomNum.toString();
       
       
       *//**
        * loading of images 
        *//*
       
       
       try (InputStream inputStream = new FileInputStream(new File(sourceImage1))) {
           sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }
        catch(Exception e)
        {
            System.out.println("Failed to load source image " + sourceImage1);   
            System.exit(1);
        }
       
      
       
       try (InputStream inputStream = new FileInputStream(new File(intermediateImage))) {
    	   intermediateImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }
        catch(Exception e)
        {
            System.out.println("Failed to load target image " + intermediateImage);   
            System.exit(1);
        }
       
       
       
       Image source=new Image()
       .withBytes(sourceImageBytes);
       
      
       
       Image intermediate=new Image()
       .withBytes(intermediateImageBytes);
       
       *//**
        * end of loading of images
        *//*
       
       
*//**
 * creating of random collection id 
 *//*
       
       
   CreateCollectionRequest request = new CreateCollectionRequest()
               .withCollectionId(collectionId);
   System.out.println("creting collection id");
      System.out.println(request);

CreateCollectionResult createCollectionResult = rekognitionClient.createCollection(request); 
System.out.println("CollectionArn : " +
   createCollectionResult.getCollectionArn());




// start of indexing and listing of images 

IndexFacesRequest indexFacesRequest = new IndexFacesRequest()
       .withImage(source)
       .withQualityFilter(QualityFilter.NONE)
       .withMaxFaces(2)
       .withCollectionId(collectionId)
       .withExternalImageId("pic1")
       .withDetectionAttributes("DEFAULT");



IndexFacesRequest indexFacesRequestIntermediate = new IndexFacesRequest()
.withImage(intermediate)
.withQualityFilter(QualityFilter.NONE)
.withMaxFaces(2)
.withCollectionId(collectionId)
.withExternalImageId("intermediate")
.withDetectionAttributes("DEFAULT");



rekognitionClient.indexFaces(indexFacesRequest);
IndexFacesResult indexFacesResult = rekognitionClient.indexFaces(indexFacesRequestIntermediate);



ListFacesRequest listFacesRequest = new ListFacesRequest()
.withCollectionId(collectionId);

ObjectMapper objectMapper = new ObjectMapper();

ListFacesResult listFacesResult =  rekognitionClient.listFaces(listFacesRequest);
List < Face > faces = listFacesResult.getFaces();
for (Face face: faces) {
	System.out.println("*** listing of faces here *****");
   try {
	System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
	      .writeValueAsString(face));
} catch (JsonProcessingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
} 

// end of indexing and listing
return collectionId;

   }
	   
	   public static void main(String[] args) throws Exception{
		   
		   
	   
	   }
 
}a*/