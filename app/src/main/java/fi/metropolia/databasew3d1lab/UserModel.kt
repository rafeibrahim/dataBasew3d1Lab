package fi.metropolia.databasew3d1lab

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserModel (application: Application): AndroidViewModel(application) {
    private val users: LiveData<List<User>> =
        UserDB.get(getApplication()).userDao().getAll()

    //private val contactDetails: LiveData<UserContact> =
      //  UserDB.get(getApplication()).userDao().getUserContacts(3)

    fun getUsers() = users

    fun getContactDetails(uid: Long) =
        UserDB.get(getApplication()).userDao().getUserContacts(uid)
}