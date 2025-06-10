package com.example.pixelart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Prototype activity for pixel art drawing. Provides minimal
 * layer management, mask support and a basic color picker circle.
 */
class PixelArtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pixel_art)
    }
}
