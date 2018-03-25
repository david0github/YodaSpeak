package ser210.quinnipiac.edu.yodaspeak;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by davidnguyen on 3/18/18.
 */

public class YodaSpeak {

    public static final String YODA_SPEAK = "YODA_SPEAK";

    public YodaSpeak() {

    }

    public static String getYodaSpeak(String yodaSpeakJsonString) throws JSONException {
        JSONObject yodaSpeakJsonObject = new JSONObject(yodaSpeakJsonString);

        return YODA_SPEAK;
    }
}
