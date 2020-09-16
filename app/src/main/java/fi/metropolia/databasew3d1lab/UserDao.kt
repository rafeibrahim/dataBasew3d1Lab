package fi.metropolia.databasew3d1lab

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Query("SELECT * FROM user WHERE user.uid = :userid")
    // the @Relation do the INNER JOIN for you ;)
    fun getUserContacts(userid: Long): LiveData<UserContact>
}