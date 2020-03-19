package tech.bencloud.double_threaded;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int maxValue = 4000;
    int upValue = 0;
    int downValue = maxValue;
    int increment = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView upTV = findViewById(R.id.upTV);
        final TextView downTV = findViewById(R.id.downTV);

        TimerTask upTask = new TimerTask() {
            @Override
            public void run() {
                upValue += increment;
                upTV.post(new Runnable() {
                    @Override
                    public void run() {
                        upTV.setText(Integer.toString(upValue));
                    }
                });
            }
        };

        TimerTask downTask = new TimerTask() {
            @Override
            public void run() {
                downValue -= increment;
                downTV.post(new Runnable() {
                    @Override
                    public void run() {
                        downTV.setText(Integer.toString(downValue));
                    }
                });
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(upTask, 0, 1000L);
        timer.scheduleAtFixedRate(downTask, 0, 1000L);

    }
}
