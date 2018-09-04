package gr.osnet.statusbarexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dummy_1.setOnClickListener {
            status_bar.slideUpDown(resources.getColor(R.color.colorGreen), newText = "Connected")
            status_bar2.slideDownUp(resources.getColor(R.color.colorGreen), newText = "Connected")
        }
        dummy_2.setOnClickListener {
            status_bar.slideUpDown(resources.getColor(R.color.colorPurple), newText = "Download Complete!")
            status_bar2.slideDownUp(resources.getColor(R.color.colorPurple), newText = "Download Complete!")
        }
        dummy_3.setOnClickListener {
            status_bar.slideUpDown(resources.getColor(R.color.colorRed), newText = "Disconnected")
            status_bar2.slideDownUp(resources.getColor(R.color.colorRed), newText = "Disconnected")
        }

        hide_up.setOnClickListener {
            status_bar.hideUp()
        }

        hide_down.setOnClickListener {
            status_bar2.hideDown()
        }
    }
}
