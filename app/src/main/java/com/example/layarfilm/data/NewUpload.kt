package com.example.layarfilm.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewUpload (
    val title: String?,
    val thumbnail: String?,
    val duration: String?
): Parcelable

