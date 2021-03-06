package com.mytest.myapplication.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoInfo(
    val author: String?,
    val name: String?,
    val description: String?,
    val avatar: String?,
    val url: String?
) : Parcelable



