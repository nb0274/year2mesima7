package com.example.year2mesima7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "rawtext.txt";
    TextView display;
    EditText input;
    int resourceId;
    String fileName, line;
    InputStream iS;
    InputStreamReader iSR;
    BufferedReader bR;
    StringBuilder sB;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.displayTv);
        input = (EditText) findViewById(R.id.inputEt);

        fileName = FILENAME.substring(0, FILENAME.length() - 4);
        resourceId = this.getResources().getIdentifier(fileName, "raw",
                this.getPackageName());
    }

    /**
     * this method read from the raw file and display it on the text view
     * @param view
     */
    public void readFromFile(View view) {
        try {
            // Initiates the variables using to read
            iS = this.getResources().openRawResource(resourceId);
            iSR = new InputStreamReader(iS);
            bR = new BufferedReader(iSR);
            sB = new StringBuilder();

            // Reads from the file
            line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            display.setText(sB.toString());
            bR.close();
            iSR.close();
            iS.close();
        }
        catch (IOException e){
            Toast.makeText(this, "Error, couldn't read file", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "Error, couldn't read file");
        }
    }

    /**
     * this method read from the edit text and display it on the text view
     * @param view
     */
    public void readFromInput(View view) {
        display.setText(input.getText().toString());
    }

    /**
     * This method is called when the user clicks on the menu, it inflates the menu onto the screen
     * @param menu the menu to move between activities
     * @return true or false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activities,menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method is called when the credits option on the menu is called and it goes to the credits activity
     * @param item the option from the menu that presents the activity the user wants to move to
     * @return true or false
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.Credits){
            in = new Intent(this, Credits.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }
}