package gr.osnet.statusbarexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        status_bar.text = "Starting Communication"
        status_bar.drawableColor = resources.getColor(R.color.colorBlue)
        status_bar.slide()
        dummy_1.setOnClickListener {
            status_bar.slide(resources.getColor(R.color.colorGreen), newText = "Connected")
        }
        dummy_2.setOnClickListener {
            status_bar.slide(resources.getColor(R.color.colorPurple), newText = "Download Complete!")
        }
        dummy_3.setOnClickListener {
            status_bar.slide(resources.getColor(R.color.colorRed), newText = "Disconnected")
        }
    }
}
