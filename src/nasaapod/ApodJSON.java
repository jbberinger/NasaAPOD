package nasaapod;

/**
 * This class is used by Google's GSON library to convert the APOD JSON into
 * a Java object.
 * 
 * @author Justin Beringer
 */
public class ApodJSON {

    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public String getURL() {
        return url;
    }

    public String getHDURL() {
        return hdurl;
    }

    public String getMediaType() {
        return media_type;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDate() {
        return date;
    }

    public String getBasicInfo() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Date: ").append(date).append("\n")
                .append("Title: ").append(title).append("\n")
                .append("Explanation: ").append(explanation).append("\n");
        return stringBuilder.toString();

    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Date: ").append(date).append("\n")
                .append("title: ").append(title).append("\n")
                .append("explanation: ").append(explanation).append("\n")
                .append("media_type: ").append(media_type).append("\n")
                .append("service_version: ").append(service_version).append("\n")
                .append("hdurl: ").append(hdurl).append("\n")
                .append("url: ").append(url);

        return stringBuilder.toString();

    }

}
