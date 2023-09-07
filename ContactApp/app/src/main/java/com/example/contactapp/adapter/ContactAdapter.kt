package com.example.contactapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.ContactListItem2Binding
import com.example.contactapp.databinding.ContactListItemBinding
import com.example.contactapp.databinding.FragmentContactGridListNameBinding
import com.example.contactapp.model.Contact
import com.example.contactapp.ui.activity.MainActivity
import com.example.contactapp.ui.dialog.ConfirmDialog
import com.example.contactapp.ui.dialog.ContactDeleteListener


class ContactAdapter(private var contactList: List<Contact>, private val contactDeleteListener: ContactDeleteListener, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun getContact(position: Int): Contact {
        return contactList[position]
    }


    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var layoutType = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return when (viewType) {
            multi_type1 -> {
                val binding =
                    ContactListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                MultiViewHolder1(binding)
            }

            multi_type2 -> {
                val binding =
                    ContactListItem2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                MultiViewHolder2(binding)
            }

            else -> {
                val binding = FragmentContactGridListNameBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                MultiViewHolder3(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (layoutType) {
            VIEW_TYPE_LINEAR -> when (position % 2) {
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


        holder.itemView.setOnLongClickListener {
            val dialog = ConfirmDialog(contactList[position], contactDeleteListener)
            dialog.isCancelable = false
            dialog.show((context as MainActivity).supportFragmentManager, "ConfirmDialog")
            true
        }

        when (layoutType) {
            VIEW_TYPE_LINEAR -> when (position % 2) {
                0 -> {
                    (holder as MultiViewHolder2).bind(contactList[position])
                }


                1 -> {
                    (holder as MultiViewHolder1).bind(contactList[position])
                }

                else -> {
                    (holder as MultiViewHolder1).bind(contactList[position])
                }
            }

            VIEW_TYPE_GRID -> {
                (holder as MultiViewHolder3).bind(contactList[position])
            }

            else -> {
                (holder as MultiViewHolder1).bind(contactList[position])
            }
        }

//        val like = holder.binding.imgListLike
//        val like2 = holder.binding.imgList2Like
//
//        if (contactList[adapterPosition].favorite)
//            like.setImageResource(
//                binding.root.context.resources.getIdentifier(
//                    "baseline_favorite_24",
//                    "drawable",
//                    binding.root.context.packageName
//                )
//            )
//        else
//            like.setImageResource(
//                binding.root.context.resources.getIdentifier(
//                    "baseline_favorite_border_24",
//                    "drawable",
//                    binding.root.context.packageName
//                )
//            )


    }

    inner class MultiViewHolder1(val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name: TextView = binding.txtListName
        private val profile: ImageView = binding.imgListProfile
        private val like: ImageView = binding.imgListLike
        var phoneNumber = "00000000000"
        val swipeTarget = binding.swipeLayout

        fun bind(item: Contact) {
            name.text = item.name
            phoneNumber = contactList[adapterPosition].phone
            profile.setImageResource(
                binding.root.context.resources.getIdentifier(
                    contactList[adapterPosition].profile,
                    "drawable",
                    binding.root.context.packageName
                )
            )

            if (contactList[adapterPosition].favorite) {
                like.setImageResource(
                    binding.root.context.resources.getIdentifier(
                        "baseline_favorite_24",
                        "drawable",
                        binding.root.context.packageName
                    )
                )
            } else {
                like.setImageResource(
                    binding.root.context.resources.getIdentifier(
                        "baseline_favorite_border_24",
                        "drawable",
                        binding.root.context.packageName
                    )
                )
            }

            like.setOnClickListener {
                val contact = contactList[adapterPosition]
                contact.favorite = !contact.favorite

                if (contact.favorite) {
                    like.setImageResource(
                        binding.root.context.resources.getIdentifier(
                            "baseline_favorite_24",
                            "drawable",
                            binding.root.context.packageName
                        )
                    )
                } else {
                    like.setImageResource(
                        binding.root.context.resources.getIdentifier(
                            "baseline_favorite_border_24",
                            "drawable",
                            binding.root.context.packageName
                        )
                    )
                }
            }


        }
    }

    inner class MultiViewHolder2(val binding: ContactListItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name2: TextView = binding.txtList2Name
        private val profile: ImageView = binding.imgList2Profile
        private val like: ImageView = binding.imgList2Like
        var phoneNumber = "00000000000"
        val swipeTarget = binding.swipeLayout2

        fun bind(item: Contact) {
            name2.text = item.name
            phoneNumber = contactList[adapterPosition].phone
            profile.setImageResource(
                binding.root.context.resources.getIdentifier(
                    contactList[adapterPosition].profile,
                    "drawable",
                    binding.root.context.packageName
                )
            )

            if (contactList[adapterPosition].favorite) {
                like.setImageResource(
                    binding.root.context.resources.getIdentifier(
                        "baseline_favorite_24",
                        "drawable",
                        binding.root.context.packageName
                    )
                )
            } else {
                like.setImageResource(
                    binding.root.context.resources.getIdentifier(
                        "baseline_favorite_border_24",
                        "drawable",
                        binding.root.context.packageName
                    )
                )
            }

            like.setOnClickListener {
                val contact = contactList[adapterPosition]
                contact.favorite = !contact.favorite

                if (contact.favorite) {
                    like.setImageResource(
                        binding.root.context.resources.getIdentifier(
                            "baseline_favorite_24",
                            "drawable",
                            binding.root.context.packageName
                        )
                    )
                } else {
                    like.setImageResource(
                        binding.root.context.resources.getIdentifier(
                            "baseline_favorite_border_24",
                            "drawable",
                            binding.root.context.packageName
                        )
                    )

                }
            }

        }
    }

    inner class MultiViewHolder3(val binding: FragmentContactGridListNameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name: TextView = binding.txtGridName
        private val profile: ImageView = binding.imgGridProfile

        fun bind(item: Contact) {
            name.text = item.name
            profile.setImageResource(
                binding.root.context.resources.getIdentifier(
                    contactList[adapterPosition].profile,
                    "drawable",
                    binding.root.context.packageName
                )
            )


        }
    }

    companion object {
        const val VIEW_TYPE_LINEAR = 1
        const val VIEW_TYPE_GRID = 2
        const val multi_type1 = 1
        const val multi_type2 = 2
        const val multi_type3 = 3
    }

    fun setContactList(newContactList: List<Contact>) {
        contactList = newContactList
        notifyDataSetChanged()
        Log.d("abcd", "setcontactList")
    }




}