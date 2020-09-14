package fi.metropolia.databasew3d1lab

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

class UserAdapter(private val items: List<User>?): RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
    )

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.textView.text = items?.get(position).toString()
        items?.get(position)?.uid

    }


}