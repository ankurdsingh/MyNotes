package com.ankur.mynotes.persistence

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "table_note")
class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String?,
    var description: String?,
    val createdOn: Long,
    var tag: String?
):Parcelable