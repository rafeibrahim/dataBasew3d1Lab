package fi.metropolia.databasew3d1lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val db by lazy { UserDB.get(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_users.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        val ump = ViewModelProviders.of(this).get(UserModel::class.java)
        ump.getUsers().observe(this, {contactList ->

            rv_users.adapter = UserAdapter(contactList?.sortedBy {that ->
                that.lastname
            })
        })




        button.setOnClickListener{saveEntry()}

    }

    private fun saveEntry () {
        var firstName = fname.text.toString()
        var secondName = lname.text.toString()
        GlobalScope.launch {
            val id = db.userDao().insert(User(0, firstName, secondName))
            db.contactDao().insert(ContactInfo(id, "twitter", "@jdoe"))
            //db.contactDao().insert(ContactInfo(id, "phone", "1234"))
            withContext(Main) {
                //txtDbInsert.text = "User saved with id: $id"
                Log.d("DB", "User saved with id: $id")
            }
        }
    }
}







