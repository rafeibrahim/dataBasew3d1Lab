package fi.metropolia.databasew3d1lab

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*


class ContactDetailViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView)

class ContactDetailAdapter(private val items: List<ContactInfo>): RecyclerView.Adapter<ContactDetailViewHolder>() {

    override fun getItemCount() = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ContactDetailViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
    )

    override fun onBindViewHolder(holder: ContactDetailViewHolder, position: Int) {
        holder.itemView.textView.text = items?.get(position).toString()
    }
}


