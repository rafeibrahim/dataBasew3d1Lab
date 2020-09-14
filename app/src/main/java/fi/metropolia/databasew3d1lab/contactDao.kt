package fi.metropolia.databasew3d1lab

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface contactDao {
    @Query("SELECT * FROM contactinfo")
    fun getAll(): LiveData<List<ContactInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contactInfo: ContactInfo)

    @Update
    fun update(contactInfo: ContactInfo)
}