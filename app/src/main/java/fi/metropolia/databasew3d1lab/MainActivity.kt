package fi.metropolia.databasew3d1lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val db by lazy { UserDB.get(this)}
    val host = NavHostFragment.create(R.navigation.welcome_nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val host = NavHostFragment.create(R.navigation.welcome_nav_graph)

        supportFragmentManager.beginTransaction().replace(R.id.flfragment, host).setPrimaryNavigationFragment(host).commit()


        val fragmentAddUser = AddUserFragment()
        val fragmentUserList = FragmentUserList()

        btnAddUser.setOnClickListener {
/*            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, fragmentAddUser)
                addToBackStack(null)
                commit()
                            val action =
                FragmentUserListDirections.actionFragmentUserListToFragmentContactDetails(items?.get(position)?.uid!!.toInt(), items?.get(position).toString())
            it.findNavController().navigate(action)
            }*/
            //supportFragmentManager.beginTransaction().replace(R.id.flfragment, host)
            //MainActivityDirections
            //val navController = Navigation.findNavController(this, R.id.welcome_nav_graph)
            host.navController.navigate(R.id.addUserFragment)
            //it.findNavController().navigate(R.id.action_fragmentUserList_to_addUserFragment)

        }

        btnUserList.setOnClickListener {
            host.navController.navigate((R.id.fragmentUserList))
        }

        btnTest.isEnabled = false
        btnTest.isClickable = false

/*        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val fragmentUserList = FragmentUserList()
        val fragmentAddUser = AddUserFragment()

        // we need to use fragment transaction

        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment, fragmentUserList)
            commit()
        }*/

/*
        btnTest.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, fragmentUserList)
                addToBackStack(null)
                commit()
            }
        }

        btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, fragmentAddUser)
                addToBackStack(null)
                commit()
            }
        }
*/

/*        btnTest.setOnClickListener {
            GlobalScope.launch {
                //val id = db.userDao().insert(User(0, "ahmed", "akram"))
                db.contactDao().insert(ContactInfo(17, "testContact", "testContact"))
                db.contactDao().insert(ContactInfo(17, "phone222", "1234456"))
                withContext(Main) {
                    //txtDbInsert.text = "User saved with id: $id"
                    Log.d("DB", "User saved with id")
                }
                val resultGetContacts = db.userDao().getUserContacts(17)


                withContext(Main) {
                    Log.d("DB", resultGetContacts.toString())
*//*                    resultGetContacts.contacts?.forEach {
                        Log.d("DB", it.user.toString())
                        Log.d("DB", it.type)
                        Log.d("DB", it.value)
                    }*//*
                }
            }
        }*/

 /*       rv_users.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        val ump = ViewModelProviders.of(this).get(UserModel::class.java)
        ump.getUsers().observe(this, {contactList ->

            rv_users.adapter = UserAdapter(contactList?.sortedBy {that ->
                that.lastname
            })
        })




        button.setOnClickListener{saveEntry()}*/

    }

/*    private fun saveEntry () {
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
    }*/
}







