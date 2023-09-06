package com.example.contactapp.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.databinding.ContactListItemBinding
import com.example.contactapp.model.Contact

class ContactAdapter(private var contactList : List<Contact>) : RecyclerView.Adapter<ContactAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)



        return Holder(binding).apply {
            binding.imgListLike.setOnClickListener{
                if (!contactList[adapterPosition].favorite) {
                    binding.imgListLike.setImageResource(R.drawable.baseline_favorite_24)
                    contactList[adapterPosition].favorite = true

                } else {
                    binding.imgListLike.setImageResource(R.drawable.baseline_favorite_border_24)
                    contactList[adapterPosition].favorite = false
                }
            }
//            binding.

        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }



        holder.name.text = contactList[position].name


        if(position % 2 == 1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.binding.root.context, android.R.color.holo_purple))

        } else {
            holder.itemView

        }



        holder.profile.setImageResource(holder.binding.root.context.resources.getIdentifier(contactList[position].profile, "drawable", holder.binding.root.context.packageName))



        if(contactList[position].favorite)
            holder.like.setImageResource(holder.binding.root.context.resources.getIdentifier("baseline_favorite_24", "drawable", holder.binding.root.context.packageName))
        else
            holder.like.setImageResource(holder.binding.root.context.resources.getIdentifier("baseline_favorite_border_24", "drawable", holder.binding.root.context.packageName))
    }

    fun setContactList(newContactList: List<Contact>) {
        contactList = newContactList
        notifyDataSetChanged()
        Log.d("abcd", "setcontactList")
    }


    inner class Holder(val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.txtListName
        val profile = binding.imgListProfile
        val like = binding.imgListLike

    }


}