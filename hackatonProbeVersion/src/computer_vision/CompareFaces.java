package computer_vision;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import com.amazonaws.util.IOUtils;

import javax.imageio.ImageIO;

public class CompareFaces
{
  public static void drawFrame(String path, double x, double y, double w, double h) throws IOException
  {
    BufferedImage image = ImageIO.read(new File(path));
    Graphics2D g2d = image.createGraphics();

    g2d.setColor(Color.GREEN);
    g2d.setStroke(new BasicStroke(10));
    g2d.draw(new Ellipse2D.Double(x * image.getWidth(), y * image.getHeight(), w * image.getWidth(), h * image.getHeight()));
    g2d.dispose();

    File f = new File("resources/out_photo/result.jpg");
    if (!f.exists()) {
      if (!f.createNewFile()) {
        throw new IOException("Error! Can't create file!");
      }
    }
    RenderedImage render = image;
    ImageIO.write(render, "jpg", f);
  }

  public static boolean compare(String sourceImage, String targetImage)
  {
    float similarityThreshold =  70;
    ByteBuffer sourceImageBytes = null;
    ByteBuffer targetImageBytes = null;

    AWSCredentials credentials;
    try
    {
      credentials = new ProfileCredentialsProvider().getCredentials();
    }
    catch (Exception e)
    {
      throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
              + "Please make sure that your credentials file is at the correct "
              + "location (/Users/userid/.aws/credentials), and is in valid format.", e);
    }

    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
            .standard()
            .withRegion(com.amazonaws.regions.Regions.US_WEST_2)
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();

    //Load source and target images and create input parameters
    try
    {
      InputStream inputStream = new FileInputStream(new File(sourceImage));
      sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
    }
    catch (Exception e)
    {
      //System.out.println("Failed to load source image " + sourceImage);
      System.exit(1);
    }

    try
    {
      InputStream inputStream = new FileInputStream(new File(targetImage));
      targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
    }
    catch (Exception e)
    {
      //System.out.println("Failed to load target images: " + targetImage);
      System.exit(1);
    }

    Image source = new Image().withBytes(sourceImageBytes);
    Image target = new Image().withBytes(targetImageBytes);

    CompareFacesRequest request = new CompareFacesRequest()
                    .withSourceImage(source)
                    .withTargetImage(target)
                    .withSimilarityThreshold(similarityThreshold);

    // Call operation
    CompareFacesResult compareFacesResult = rekognitionClient.compareFaces(request);


    // Display results
    List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
    for (CompareFacesMatch match : faceDetails)
    {
      ComparedFace face = match.getFace();
      BoundingBox position = face.getBoundingBox();
      //System.out.println("Face at " + position.getLeft().toString() + " " + position.getTop() + " matches with " + face.getConfidence().toString() + "% confidence.");
      try
      {
        drawFrame(targetImage, position.getLeft(), position.getTop(), position.getWidth(), position.getHeight());
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    List<ComparedFace> uncompared = compareFacesResult.getUnmatchedFaces();

    //System.out.println("There was " + uncompared.size() + " face(s) that did not match");
    //System.out.println("Source image rotation: " + compareFacesResult.getSourceImageOrientationCorrection());
    //System.out.println("target image rotation: " + compareFacesResult.getTargetImageOrientationCorrection());

    return !faceDetails.isEmpty();
  }
}