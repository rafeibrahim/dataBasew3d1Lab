package fi.metropolia.databasew3d1lab

import androidx.room.*

@Entity(foreignKeys = [(ForeignKey(entity = User::class, parentColumns = ["uid"], childColumns = ["user"]))])
data class ContactInfo(
    val user: Long,
    val type: String, //e.g. phone, email, fb, twitter,...
    @PrimaryKey
    val value: String)

