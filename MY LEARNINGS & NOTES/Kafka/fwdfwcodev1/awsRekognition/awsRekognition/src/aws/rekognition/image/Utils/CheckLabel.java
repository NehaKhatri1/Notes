package aws.rekognition.image.Utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Instance;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.Parent;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.util.IOUtils;

public class CheckLabel {

    public static void main(String[] args) throws Exception {

        String photo = "photo";
        String bucket = "bucket";
        ByteBuffer intermediateImageBytes=null;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIASFL2QBMYUXYO2E7F","PwbXC9XsyrObqr6AnGdLHN2Hiw+pb7coxt0uvHa8");

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
     		   .withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        
        try (InputStream inputStream = new FileInputStream(new File("/home/cloudera/Downloads/weeping.jpeg"))) {
     	   intermediateImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
         }
         catch(Exception e)
         {
             System.out.println("Failed to load target image ");   
             System.exit(1);
         }
        
      
       
        Image intermediate=new Image()
        .withBytes(intermediateImageBytes);

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(intermediate)
                .withMaxLabels(5).withMinConfidence(75F);

        try {
            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List<Label> labels = result.getLabels();

            System.out.println("Detected labels for " + photo + "\n");
            for (Label label : labels) {
                System.out.println("Label: " + label.getName());
                System.out.println("Confidence: " + label.getConfidence().toString() + "\n");

                List<Instance> instances = label.getInstances();
                System.out.println("Instances of " + label.getName());
                if (instances.isEmpty()) {
                    System.out.println("  " + "None");
                } else {
                    for (Instance instance : instances) {
                        System.out.println("  Confidence: " + instance.getConfidence().toString());
                        System.out.println("  Bounding box: " + instance.getBoundingBox().toString());
                    }
                }
                System.out.println("Parent labels for " + label.getName() + ":");
                List<Parent> parents = label.getParents();
                if (parents.isEmpty()) {
                    System.out.println("  None");
                } else {
                    for (Parent parent : parents) {
                        System.out.println("  " + parent.getName());
                    }
                }
                System.out.println("--------------------");
                System.out.println();
               
            }
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
    }
}