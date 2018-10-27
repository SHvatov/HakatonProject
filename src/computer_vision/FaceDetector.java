package computer_vision;

import com.sun.javaws.exceptions.InvalidArgumentException;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import java.util.ArrayList;

import utils.Utilities;

/**
 * This class is designed to parse photo and look for all
 * faces on this photo using OpenCV library.
 * @author Sergey Khvatov
 */
public class FaceDetector
{
  static
  {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  /**
   * Default size for the output face
   */
  private static Size size = new Size(1000, 1000);

  /**
   * Array of matrix with faces
   */
  private ArrayList<Mat> faces = new ArrayList<>();

  /**
   * Default {@link FaceDetector} class constructor from string with the path to the picture.
   * @param path Path to the picture.
   */
  public FaceDetector(String path) throws InvalidArgumentException
  {
    Integer numberOfFace = 0;
    recogniseFaces(path);
  }

  /**
   * Default {@link FaceDetector} class constructor from string array of paths to the pictures.
   * @param paths Array of paths to the pictures.
   * @throws InvalidArgumentException
   */
  public FaceDetector(String[] paths) throws InvalidArgumentException
  {
    // recognise and detect faces on the photos
    for (String path : paths)
    {
      recogniseFaces(path);
    }
    // number of recognised faces
    int num = 0;
    // DEBUG output
    for (Mat face : faces)
    {
      String filename = "debug/faces_output/picture-" + Integer.toString(num++) + ".jpg";
      System.out.println("DEBUG: picture " + filename + " was processed.");
      Imgcodecs.imwrite(filename, face);
    }
  }

  /**
   * This method is designed to access the array of matrix representations of the faces
   */
  public ArrayList<Mat> getFaces()
  {
    return faces;
  }

  /**
   * This method is designed to recognise faces on the picture.
   */
  private void recogniseFaces(String path) throws InvalidArgumentException
  {
    // check file path
    Utilities.checkFile(path);

    // create an instance of CascadeClassifier passing it the name of the file
    // from which the classifier is loaded
    CascadeClassifier faceDetector = new CascadeClassifier("resources\\lbpcascades\\lbpcascade_frontalface_improved.xml");
    // read image
    Mat image = Imgcodecs.imread(path);

    // detect faces
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);

    // add faces to the array of faces
    for (Rect rect : faceDetections.toArray())
    {
      Rect buf_rect = new Rect(rect.x - rect.width / 2, rect.y - rect.height / 2, rect.width + rect.width, rect.height + rect.height);
      // buffer image
      Mat buf_image = new Mat(image.clone(), buf_rect);
      // Change color of the image and crop it
      //Imgproc.cvtColor(buf_image, buf_image, Imgproc.COLOR_BGR2GRAY);
      //Imgproc.equalizeHist(buf_image, buf_image);
      Imgproc.resize(buf_image, buf_image, size);
      // add to the list
      faces.add(buf_image);
    }
    // DEBUG output
    System.out.println("Picture was processed!");
  }
}
