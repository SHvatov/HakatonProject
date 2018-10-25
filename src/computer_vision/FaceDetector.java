package computer_vision;

import com.sun.javaws.exceptions.InvalidArgumentException;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
   * Default extension value.
   */
  private static String DEFAULT_EXTENSION = "jpg";

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
    recogniseFaces(path);
  }

  /**
   * This method is designed to access the array of matrix representations of the faces
   */
  ArrayList<Mat> getFaces()
  {
    return faces;
  }

  /**
   * This method is designed to recognise faces on the picture.
   */
  private void recogniseFaces(String path) throws InvalidArgumentException
  {
    // check file
    if (!checkFileExtension(path))
    {
      throw new InvalidArgumentException(new String[] {
          "Error! Incorrect file extension."
      });
    }

    if (!(new File(path)).exists())
    {
      throw new InvalidArgumentException(new String[] {
          "Error! File does not exist."
      });
    }

    // create an instance of CascadeClassifier passing it the name of the file
    // from which the classifier is loaded.
    CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
    // read image
    Mat image = Imgcodecs.imread(path);

    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);

    for (Rect rect : faceDetections.toArray())
    {
      faces.add(new Mat(image.clone(), rect));
    }
  }

  /**
   * This method is designed to check whether extension of the file with the picture is
   * suitable for further algorithm working or not.
   * @param path Path to the file.
   * @return True, if extension is correct, false otherwise.
   */
  private boolean checkFileExtension(String path)
  {
    String[] parts = path.split("\\.");
    return parts[parts.length - 1].equals(DEFAULT_EXTENSION)
        || parts[parts.length - 1].equals((DEFAULT_EXTENSION.toUpperCase()));
  }
}
