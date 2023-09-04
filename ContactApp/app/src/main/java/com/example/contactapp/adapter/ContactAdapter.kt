package com.example.contactapp.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.databinding.ContactListItemBinding
import com.example.contactapp.model.Contact

class ContactAdapter(private val contactList : List<Contact>) : RecyclerView.Adapter<ContactAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val contact = contactList[position]


        holder.name.text = contactList[position].name
        holder.profile.setImageResource(holder.binding.root.context.resources.getIdentifier(contactList[position].profile, "drawable", holder.binding.root.context.packageName))



        if(contactList[position].favorite)
            holder.like.setImageResource(holder.binding.root.context.resources.getIdentifier("baseline_favorite_24", "drawable", holder.binding.root.context.packageName))
        else
            holder.like.setImageResource(holder.binding.root.context.resources.getIdentifier("baseline_favorite_border_24", "drawable", holder.binding.root.context.packageName))
    }

    inner class Holder(val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.txtListName
        val profile = binding.imgListProfile
        val like = binding.imgListLike

    }


}