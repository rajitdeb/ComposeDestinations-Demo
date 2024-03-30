package com.rajit.composedestinationsdemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val address: String,
    val age: Int,
    val bio: String,
    val countryOfResidence: String,
    val profileImgURL: String,
    val projects: List<String>?,
    val socialLinks: List<String>
): Parcelable
