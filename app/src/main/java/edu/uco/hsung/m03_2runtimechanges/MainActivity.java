package edu.uco.hsung.m03_2runtimechanges;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button clickButton;
    private Button clickButtonNoSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        clickButton = (Button) findViewById(R.id.button_count);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = clickButton.getText().toString();
                int count = Integer.parseInt(label);
                Resources res = getResources();
                String text = String.format(res.getString(R.string.saved_count), count + 1);
                clickButton.setText(text);
            }
        });

        clickButtonNoSave = (Button) findViewById(R.id.button_count_nosave);
        clickButtonNoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = clickButtonNoSave.getText().toString();
                int count = Integer.parseInt(label);
                Resources res = getResources();
                String text = String.format(res.getString(R.string.unsaved_count), count + 1);
                clickButtonNoSave.setText(text);
            }
        });

        if (savedInstanceState != null) {
            // this Activity is restarted. Find any saved states.
            String buttonLabel = savedInstanceState.getString("COUNT");
            if (buttonLabel != null) {
                clickButton.setText(buttonLabel);
            }
        }
    }

    // this is called before this Activity is destroyed
    // Note the state of 'clickButtonNosave' is not saved
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String count = clickButton.getText().toString();
        outState.putString("COUNT", count);
    }
}