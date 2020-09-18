package fi.metropolia.databasew3d1lab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_contact_details.*
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentContactDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentContactDetails : Fragment(R.layout.fragment_contact_details) {

    private var uid: Int = 0
    private lateinit var details: String
    private val db by lazy { UserDB.get(requireActivity())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val args: FragmentContactDetailsArgs by navArgs()
        tvUserDetails.text = args.name
        uid = args.uid
        details = args.name
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.contact_types,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.setSelection(1)
        rvcontactDetails.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)

        val ump = ViewModelProviders.of(this).get(UserModel::class.java)
        ump.getContactDetails(uid.toLong()).observe(this, {contactList ->
            contactList.contacts?.forEach {
                Log.d("DB", it.user.toString())
                Log.d("DB", it.type)
                Log.d("DB", it.value)
            }
            rvcontactDetails.adapter = ContactDetailAdapter(contactList.contacts!!)
        })
        btnSaveContact.setOnClickListener {
            saveContactDetails()
        }
    }

    private fun saveContactDetails(){
       val type =  spinner.selectedItem.toString()
        val detail = ptContactDetails.text.toString()
        Log.d("DB", type)
        Log.d("DB", "detail: $detail")

        if (!detail.isNullOrEmpty()) {
            GlobalScope.launch {
                //val id = db.userDao().insert(User(0, "ahmed", "akram"))
                db.contactDao().insert(ContactInfo(uid.toLong(), type, detail))
                }
            }
        }
}
