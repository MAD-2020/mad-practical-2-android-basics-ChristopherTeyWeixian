package sg.edu.np.WhackAMole;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Whack-A-Mole";
    private TextView result;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private List<Button> ButtonList = new ArrayList<>();
    private Integer randomLocation;
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(TextView)findViewById(R.id.result);
        Button1=(Button)findViewById(R.id.button1);
        Button2=(Button)findViewById(R.id.button2);
        Button3=(Button)findViewById(R.id.button3);
        ButtonList.add(Button1);
        ButtonList.add(Button2);
        ButtonList.add(Button3);
        Log.v(TAG, "Finished Pre-Initialisation!");
    }


    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button Pressed=(Button) v;
                Log.v(TAG,"Reached");
                switch(ButtonList.indexOf(Pressed)){
                    case 0:
                        Log.v(TAG,"Button Left Clicked!");
                        break;
                    case 1:
                        Log.v(TAG,"Button Middle Clicked!");
                        break;
                    case 2:
                        Log.v(TAG,"Button Right Clicked!");
                        break;
                    default:
                        Log.v(TAG,"Unknown button pressed.");
                }
                Integer score=Integer.parseInt(result.getText().toString());
                switch(Pressed.getText().toString()){
                    case "0":
                        Log.v(TAG,"Missed, score deducted!");
                        score -= 1;
                        result.setText(score.toString());
                        break;
                    case "*":
                        Log.v(TAG,"Hit, score added!");
                        score += 1;
                        result.setText(score.toString());
                        break;
                    default:
                        Log.v(TAG,"Unknown button pressed.");
                }
                ResetMole();
                setNewMole();
            }
        };
        Button1.setOnClickListener(listener);
        Button2.setOnClickListener(listener);
        Button3.setOnClickListener(listener);

        Log.v(TAG, "Starting GUI!");

    }


    public void setNewMole()//Place new "*"
    {
        Random ran = new Random();
        randomLocation = ran.nextInt(3);
        ButtonList.get(randomLocation).setText("*");
    }
    public void ResetMole()
    {
        ButtonList.get(randomLocation).setText("0");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"Game is paused.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"Resuming the game.");
    }

    @Override //Clear the score when exiting the file
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"Exiting game...");
    }

}
