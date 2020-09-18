package fi.metropolia.databasew3d1lab

import android.content.Context
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

class UserAdapter(private val items: List<User>?, private val context: Context): RecyclerView.Adapter<UserViewHolder>() {
    private val db by lazy { UserDB.get(context)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
    )

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.textView.text = items?.get(position).toString()
        holder.itemView.setOnClickListener {
            // do something
            //rv_users.setOnClickListener {
            Log.d("DB", "Item clicked")

            val action =
                FragmentUserListDirections.actionFragmentUserListToFragmentContactDetails(items?.get(position)?.uid!!.toInt(), items?.get(position).toString())
            it.findNavController().navigate(action)
        }
    }
}


