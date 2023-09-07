package com.example.contactapp.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactapp.R
import com.example.contactapp.databinding.ContactListItem2Binding
import com.example.contactapp.databinding.ContactListItemBinding
import com.example.contactapp.databinding.FragmentContactGridListBinding
import com.example.contactapp.databinding.FragmentContactListBinding
import com.example.contactapp.model.Contact
import java.util.Collections


class ContactAdapter(private var contactList : List<Contact>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun getContact(position : Int) : Contact  {
        return contactList[position]
    }


    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null
    var layoutType = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return when(viewType) {
            multi_type1 -> {
                val binding =
                    ContactListItemBinding.inflate(LayoutInflater.from(parent.context),
                     parent, false)
                MultiViewHolder1(binding)
            }

            multi_type2 -> {
                val binding =
                ContactListItem2Binding.inflate(LayoutInflater.from(parent.context),
                    parent, false)
                MultiViewHolder2(binding)
            }

            else -> {
                val binding = FragmentContactGridListBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false)
                MultiViewHolder3(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(layoutType) {
            VIEW_TYPE_LINEAR -> when(position % 2) {
                0 -> multi_type2
                1 -> multi_type1
                else -> multi_type1
            }
            VIEW_TYPE_GRID -> multi_type3
            else -> multi_type1
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        when(layoutType) {
          VIEW_TYPE_LINEAR -> when(position % 2) {
              0 -> {(holder as MultiViewHolder2).bind(contactList[position])}
              1 -> {(holder as MultiViewHolder1).bind(contactList[position])}
              else -> {(holder as MultiViewHolder1).bind(contactList[position])}
          }
            VIEW_TYPE_GRID -> {(holder as MultiViewHolder3).bind(contactList[position])}
            else -> {(holder as MultiViewHolder1).bind(contactList[position])}
        }

    }
    inner class MultiViewHolder1(val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val name : TextView = binding.txtListName
        private val profile : ImageView = binding.imgListProfile
        private val like : ImageView = binding.imgListLike

        fun bind(item: Contact) {
            name.text = item.name
            profile.setImageResource(binding.root.context.resources.getIdentifier(contactList[adapterPosition].profile, "drawable", binding.root.context.packageName))

//            Glide.with(itemView).load(profile).into(binding.imgListProfile)
//            Glide.with(itemView).load(like).into(binding.imgListLike)

            if(contactList[adapterPosition].favorite)
                like.setImageResource(binding.root.context.resources.getIdentifier("baseline_favorite_24", "drawable", binding.root.context.packageName))
            else
                like.setImageResource(binding.root.context.resources.getIdentifier("baseline_favorite_border_24", "drawable", binding.root.context.packageName))

        }
    }
    inner class MultiViewHolder2(val binding: ContactListItem2Binding) : RecyclerView.ViewHolder(binding.root) {

        private val name : TextView = binding.txtList2Name
        private val profile : ImageView = binding.imgList2Profile
        private val like : ImageView = binding.imgList2Like

        fun bind(item: Contact) {
            name.text = item.name
            profile.setImageResource(binding.root.context.resources.getIdentifier(contactList[adapterPosition].profile, "drawable", binding.root.context.packageName))


            if(contactList[adapterPosition].favorite)
                like.setImageResource(binding.root.context.resources.getIdentifier("baseline_favorite_24", "drawable", binding.root.context.packageName))
            else
                like.setImageResource(binding.root.context.resources.getIdentifier("baseline_favorite_border_24", "drawable", binding.root.context.packageName))

        }
    }

    inner class MultiViewHolder3(val binding: FragmentContactGridListBinding) : RecyclerView.ViewHolder(binding.root) {

        private val name : TextView = binding.txtGridName
        private val profile : ImageView = binding.imgGridProfile

        fun bind(item: Contact) {
            name.text = item.name
            profile.setImageResource(binding.root.context.resources.getIdentifier(contactList[adapterPosition].profile, "drawable", binding.root.context.packageName))




        }
    }
    companion object {
        private const val VIEW_TYPE_LINEAR = 1
        private const val VIEW_TYPE_GRID = 2
        private const val multi_type1 = 1
        private const val multi_type2 = 2
        private const val multi_type3 = 3


    }

    fun setContactList(newContactList: List<Contact>) {
        contactList = newContactList
        notifyDataSetChanged()
        Log.d("abcd", "setcontactList")
    }

}