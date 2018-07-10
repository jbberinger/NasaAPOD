package nasaapod;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Justin Beringer
 */
public class Apod {

    private String API_KEY;
    private final String APOD_QUERY_URL = "https://api.nasa.gov/planetary/apod?api_key=";
    private static final Logger LOGGER = Logger.getLogger(Apod.class.getName());
    private Image astronomyImage;
    private ApodJSON apodJSON;

    public Apod() {
        retrieveData();
    }

    /**
     * Retrieves APOD JSON data using APOD API
     */
    private void retrieveData() {

        Gson gson = new Gson();
        BufferedReader bufferedReader;
        String jsonLine;
        String json = "";
        HttpURLConnection httpUrlConnection;
        InputStream inputStream;
        BufferedImage bufferedImage;
        API_KEY = getAPIKey();

        try {

            URL queryURL = new URL(APOD_QUERY_URL + API_KEY);
            bufferedReader = new BufferedReader(new InputStreamReader(queryURL.openStream()));

            while ((jsonLine = bufferedReader.readLine()) != null) {
                json += jsonLine;
            }

            apodJSON = gson.fromJson(json, ApodJSON.class);
            LOGGER.info(apodJSON.toString());

            // Default image is loaded if apod is a video.
            if (apodJSON.getMediaType().equals("image")) {
                URL imageURL = new URL(apodJSON.getHDURL());
                httpUrlConnection = (HttpURLConnection) imageURL.openConnection();
                inputStream = httpUrlConnection.getInputStream();
                bufferedImage = ImageIO.read(inputStream);
                astronomyImage = (Image) bufferedImage;
            } else {
                bufferedImage = ImageIO.read(getClass().getResource("apod_default.jpg"));
                astronomyImage = (Image) bufferedImage;
            }

        } catch (IOException | JsonSyntaxException e) {
            LOGGER.log(Level.SEVERE, "Caught exception: {0}", e.getMessage());
        }

    }

    public Image getImage() {
        return astronomyImage;
    }

    /**
     * returns date, title, and description of image
     */
    public String getBasicInfo() {
        return apodJSON.getBasicInfo();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Retrieves API Key from text file.
     */
    public String getAPIKey() {
        String apiKey = null;
        try {
            InputStream in = this.getClass().getResourceAsStream("NASA_API_Key.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            apiKey = reader.readLine();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Caught exception: {0}", e.getMessage());
        }
        return apiKey;
    }

}
