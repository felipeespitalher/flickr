package com.tigerspike.extension

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.OutputStream

fun Bitmap.persistTempoary(context: Context, uri: Uri?): OutputStream? {
    val outStream = context.contentResolver.openOutputStream(uri)
    compress(Bitmap.CompressFormat.JPEG, 100, outStream)
    outStream?.close()
    return outStream
}