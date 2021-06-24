package com.itigraduationteam.madartask.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    val mName: String?,
    val mAge: Int?,
    val mJopTitle: String?,
    val mGender: String?
) : Serializable{
    @PrimaryKey(autoGenerate = true)
    var mId: Int? = null
}