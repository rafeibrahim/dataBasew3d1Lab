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
class FragmentContactDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var uid: Int = 0
    private lateinit var details: String
    private val db by lazy { UserDB.get(requireActivity())}


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val args: FragmentContactDetailsArgs by navArgs()
        tvUserDetails.text = args.name
        uid = args.uid
        details = args.name
        // val contactTypes = resources.getStringArray(R.array.contact_types)
        // val spinner: Spinner = requireActivity().findViewById(R.id.spinner)
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
                //db.contactDao().insert(ContactInfo(17, "phone222", "1234456"))
                withContext(Dispatchers.Main) {
                    //txtDbInsert.text = "User saved with id: $id"
                    Log.d("DB", "Contact info saved with  id $uid")
                }
                val resultGetContacts = db.userDao().getUserContacts(uid.toLong())


                withContext(Dispatchers.Main) {
                    Log.d("DB", resultGetContacts.toString())
/*                    resultGetContacts.contacts?.forEach {
                        Log.d("DB", it.user.toString())
                        Log.d("DB", it.type)
                        Log.d("DB", it.value)
                    }*/
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentContactDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentContactDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}