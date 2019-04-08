package com.astudio.intenttogallery

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val GALLERY_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.button) as Button

        button.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)

        var bitmap: Bitmap? = null
        val imageView = findViewById(R.id.image_view) as ImageView


        when (requestCode) {
            GALLERY_REQUEST -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage = imageReturnedIntent!!.data
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                imageView.setImageBitmap(bitmap)
            }
        }

    }
}
