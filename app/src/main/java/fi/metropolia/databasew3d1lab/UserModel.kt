package fi.metropolia.databasew3d1lab

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserModel (application: Application): AndroidViewModel(application) {
    private val users: LiveData<List<User>> =
        UserDB.get(getApplication()).userDao().getAll()

    fun getUsers() = users
}