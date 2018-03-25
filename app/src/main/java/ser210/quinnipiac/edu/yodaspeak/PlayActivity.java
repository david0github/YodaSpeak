package ser210.quinnipiac.edu.yodaspeak;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;


public class PlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        String yodaSpeak = (String) intent.getExtras().get(YodaSpeak.YODA_SPEAK);
        TextView messageView = (TextView) findViewById(R.id.yodaSpeakTextView);

        messageView.setText(yodaSpeak);

    }

}
