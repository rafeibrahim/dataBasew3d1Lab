package fi.metropolia.databasew3d1lab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_add_user.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAddUser.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAddUser : Fragment(R.layout.fragment_add_user) {
    private val db by lazy { UserDB.get(requireActivity())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSave.setOnClickListener {
            saveEntry()
            it.findNavController().navigate(R.id.fragmentUserList)
        }
    }

    private fun saveEntry () {
        var firstName = ptFirstName.text.toString()
        var secondName = ptLastName.text.toString()
        GlobalScope.launch {
            val id = db.userDao().insert(User(0, firstName, secondName))
            db.contactDao().insert(ContactInfo(id, "twitterTest", "@jdoeTest"))
            //db.contactDao().insert(ContactInfo(id, "phone", "1234"))
            withContext(Main) {
                Log.d("DB", "User saved with id: $id")
            }
        }
    }
}