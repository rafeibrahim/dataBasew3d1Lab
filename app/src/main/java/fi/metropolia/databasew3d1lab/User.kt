package fi.metropolia.databasew3d1lab

import androidx.room.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Long,
    val firstname: String,
    val lastname: String) {
    //constructor, getter and setter are implicit :)
    override fun toString() = "($uid) $firstname $lastname"
}




