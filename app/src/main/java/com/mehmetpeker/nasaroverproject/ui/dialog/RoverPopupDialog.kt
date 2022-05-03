package com.mehmetpeker.nasaroverproject.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mehmetpeker.nasaroverproject.R
import com.mehmetpeker.nasaroverproject.data.model.Photo

class RoverPopupDialog(private val activity: Activity, private val item: Photo) {
    private var dialog: Dialog = Dialog(activity)

    init {
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_rover_photo)
        setupDialog()
    }

    private fun setupDialog() {
        dialog.findViewById<TextView>(R.id.tv_earth_date).text = item.earth_date
        dialog.findViewById<TextView>(R.id.tv_rover_name).text = item.rover.name
        dialog.findViewById<TextView>(R.id.tv_camera_name).text = item.camera.name
        dialog.findViewById<TextView>(R.id.tv_status).text = item.rover.status
        dialog.findViewById<TextView>(R.id.tv_launch_date).text = item.rover.launch_date
        dialog.findViewById<TextView>(R.id.tv_landing_date).text = item.rover.landing_date
        val image = dialog.findViewById<ImageView>(R.id.imageview_popup_rover)
        Glide.with(activity).load(item.img_src.replace("http", "https")).into(image)


    }

    fun showDialog() {
        if (!dialog.isShowing)
            dialog.show()
    }

}