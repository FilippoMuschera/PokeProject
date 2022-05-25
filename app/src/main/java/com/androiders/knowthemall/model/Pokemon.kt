package com.muschera.pokeapifirsttest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Pokemon(
    val name: String,
    val height: String,
    val id: Int,
    val weight: Int
) : Parcelable {
    constructor() : this("", "", 0, 0)
}