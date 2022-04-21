package com.example.myintensivetask60.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    var name: String,
    var lastName: String,
    var phoneNumber: String,
    val url: String
): Parcelable
