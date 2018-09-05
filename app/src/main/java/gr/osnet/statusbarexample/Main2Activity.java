package gr.osnet.statusbarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gr.osnet.statusbar.StatusBar;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final StatusBar status_bar = findViewById(R.id.status_bar);
        final StatusBar status_bar2 = findViewById(R.id.status_bar2);
        findViewById(R.id.dummy_1).setOnClickListener(v -> {
            status_bar.showUp(getResources().getColor(R.color.colorGreen), null, "Connected");
            status_bar2.showDown(getResources().getColor(R.color.colorGreen), null, "Connected");
        });

        findViewById(R.id.dummy_2).setOnClickListener(v -> {
            status_bar.showUp(getResources().getColor(R.color.colorPurple), null, "Download Complete!");
            status_bar2.showDown(getResources().getColor(R.color.colorPurple), null, "Download Complete!");
        });

        findViewById(R.id.dummy_3).setOnClickListener(v -> {
            status_bar.showUp(getResources().getColor(R.color.colorRed), null, "Disconnected");
            status_bar2.showDown(getResources().getColor(R.color.colorRed), null, "Disconnected");
        });

        findViewById(R.id.show).setOnClickListener(v -> {
            status_bar.show(null, null, null);
            status_bar2.show(null, null, null);
        });

        findViewById(R.id.hide_up).setOnClickListener(v -> status_bar.hideUp());

        findViewById(R.id.hide_down).setOnClickListener(v -> status_bar2.hideDown());

        findViewById(R.id.hide).setOnClickListener(v -> {
            status_bar.hide();
            status_bar2.hide();
        });


    }
}
