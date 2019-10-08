package com.haslan.bottomnav

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haslan.butterbottomnav.ButterBottomNavListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ButterBottomNavListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        butter_bottom_nav.initListener(this)
    }

    override fun tabClicked(tab: Int) {
        Toast.makeText(this, tab.toString(), Toast.LENGTH_SHORT).show()
        butter_bottom_nav.tabPositionChanged(tab)
    }

    override fun sameTabClicked(tab: Int) {
        Toast.makeText(this, "Same tab clicked", Toast.LENGTH_SHORT).show()
    }
}
