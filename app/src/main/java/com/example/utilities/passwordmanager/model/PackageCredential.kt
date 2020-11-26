package com.example.utilities.passwordmanager.model

import android.graphics.drawable.Drawable

data class PackageCredential(
    val name: String,
    val packageName: String,
    val username: String,
    val password: String,
    val icon: Drawable
)