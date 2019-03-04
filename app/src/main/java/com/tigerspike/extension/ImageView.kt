package com.tigerspike.extension

import android.graphics.Bitmap
import android.widget.ImageView

fun ImageView.toBitmap(): Bitmap {
    buildDrawingCache()
    return drawingCache
}