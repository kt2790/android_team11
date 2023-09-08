package com.example.contactapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.adapter.ContactAdapter
import com.example.contactapp.adapter.ContactListItemHelper
import com.example.contactapp.databinding.FragmentContactListBinding
import com.example.contactapp.manager.ContactManagerImpl
import com.example.contactapp.model.Contact
import com.example.contactapp.ui.activity.MainActivity
import com.example.contactapp.ui.dialog.ContactDeleteListener

class ContactListFragment : Fragment(), ContactDeleteListener {

    private var _binding: com.example.contactapp.databinding.FragmentContactListBinding? = null
    private val contactManager = ContactManagerImpl.getInstance()
    private val contactList = contactManager.getContactList()
    private val binding get() = _binding!!
    lateinit var adapter: ContactAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var itemTouchHelper: ItemTouchHelper


    //    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
//    private lateinit var currentLayoutManager: RecyclerView.LayoutManager

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).showToolbar()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerview

        (requireActivity() as MainActivity).showToolbar()

        adapter = ContactAdapter(contactList, this, requireActivity())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        itemTouchHelper = ItemTouchHelper(ContactListItemHelper(requireActivity(), adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        adapter.itemClick = object : ContactAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val detailFragment = ContactDetailFragment()

                val bundle = Bundle()
                bundle.putInt("ITEM_ID", adapter.getContact(position).id)
                detailFragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, detailFragment)
                    .addToBackStack(null)
                    .commit()

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun setRvLayout(type: Int) {
        if (this::recyclerView.isInitialized) {
            when(type) {
                1 -> {
                    recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                    adapter.layoutType = ContactAdapter.VIEW_TYPE_LINEAR
                }
                2 -> {
                    recyclerView.layoutManager = GridLayoutManager(requireActivity(), 3)
                    adapter.layoutType = ContactAdapter.VIEW_TYPE_GRID
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).hideToolbar()
        _binding = null
    }

    override fun contactDeleteListener(contact: Contact) {
        contactManager.deleteContactById(contact.id)
        adapter.setContactList(contactManager.getContactList())
    }

}
