package ser210.quinnipiac.edu.yodaspeak;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    String yodaSpeakJsonString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView myImageView = (ImageView) findViewById(R.id.imageView2);
        myImageView.setImageResource(R.drawable.yoda);
    }

    public void onClick(View view){
        EditText userInput = (EditText) findViewById(R.id.message);
        String userText = userInput.getText().toString();
        String userTextFormatted = "&sentence=" + userText.replaceAll("", "+") + "&json=true";
        new FetchYodaSpeak().execute(userTextFormatted);
    }

    private class FetchYodaSpeak extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String...params){
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String yodaSpeak = "";

            try {
                URL url = new URL("https://yoda.p.mashape.com/yoda?mashape-key=L3HG1kfRQ9mshbPKqTuBC4CYNI4tp1NxqbijsnAMp2jZJoU3bx" + params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null){

                    buffer.append(line + "\n");
                    System.out.print(buffer);

                }
                if (buffer.length() == 0){
                    return null;
                }

                yodaSpeakJsonString = buffer.toString();
                Log.i(LOG_TAG, yodaSpeakJsonString);
            } catch (IOException e){
                Log.e(LOG_TAG, "Error " + e.getMessage(), e);
                return null;
            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
                if (reader != null){
                    try {
                        reader.close();
                    } catch (final IOException e){
                        Log.e(LOG_TAG,"Error closing stream", e);
                    }
                }
            }
            if (yodaSpeakJsonString == null){
                Log.e(LOG_TAG, "Error retrieving YodaSpeak");
            }
            return yodaSpeakJsonString;
        }

        protected void onPostExecute(String result){
            Log.i(LOG_TAG, "onPostExecute");

            if(result != null) {

                Log.e(LOG_TAG, result);
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra(YodaSpeak.YODA_SPEAK, result);
                startActivity(intent);
            }
        }
    }

}
