package com.example.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

        val linearLayoutPaint = findViewById<LinearLayout>(R.id.paint_colors)
        mImageButtonCurrentPaint = linearLayoutPaint[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.pallete_selected)
        )

        val ib_brush: ImageButton = findViewById(R.id.ib_brush)
        ib_brush.setOnClickListener {
            showBrushChooserDialog()
        }

    }

    private fun showBrushChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("BrushSize: ")

        val smallbtn: ImageButton = brushDialog.findViewById(R.id.ib_small_brush)
        smallbtn.setOnClickListener {
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }

        val mediumbtn: ImageButton = brushDialog.findViewById(R.id.ib_medium_brush)
        mediumbtn.setOnClickListener {
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }

        val largebtn: ImageButton = brushDialog.findViewById(R.id.ib_large_brush)
        largebtn.setOnClickListener {
            drawingView?.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()

    }

    fun paintClick(view: View){
        if(view !== mImageButtonCurrentPaint){
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            //setting current button to be pressed
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallete_selected)
            )
            //deactivating button
            mImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallete_normal)
            )

            mImageButtonCurrentPaint = view

        }
    }
}