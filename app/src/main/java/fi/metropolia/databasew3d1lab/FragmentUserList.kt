package fi.metropolia.databasew3d1lab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_user_list.*
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentUserList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentUserList : Fragment(R.layout.fragment_user_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_users.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)

        val ump = ViewModelProviders.of(this).get(UserModel::class.java)
        ump.getUsers().observe(this, {contactList ->
            rv_users.adapter = UserAdapter(contactList?.sortedBy {that ->
                that.lastname
            }, requireActivity())
        })
        }
    }

