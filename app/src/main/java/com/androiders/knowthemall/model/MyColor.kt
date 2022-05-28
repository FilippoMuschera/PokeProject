package com.androiders.knowthemall.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MyColor(var myColor: @RawValue Color) : Parcelable {
    constructor() : this(Color.White)
}