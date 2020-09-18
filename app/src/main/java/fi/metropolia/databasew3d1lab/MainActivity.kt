package fi.metropolia.databasew3d1lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val db by lazy { UserDB.get(this)}
    val host = NavHostFragment.create(R.navigation.welcome_nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.flfragment, host).setPrimaryNavigationFragment(host).commit()

        btnAddUser.setOnClickListener {
            host.navController.navigate(R.id.addUserFragment)
        }

        btnUserList.setOnClickListener {
            host.navController.navigate((R.id.fragmentUserList))
        }

        btnTest.isEnabled = false
        btnTest.isClickable = false
    }
}







