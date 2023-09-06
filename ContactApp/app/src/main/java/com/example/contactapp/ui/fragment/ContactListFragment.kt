package com.example.contactapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.adapter.ContactAdapter
import com.example.contactapp.databinding.FragmentContactListBinding
import com.example.contactapp.manager.ContactManagerImpl

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

//    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
//    private lateinit var currentLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerview



//        linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//        gridLayoutManager = GridLayoutManager(requireActivity(), 2)
//        currentLayoutManager = linearLayoutManager
//        recyclerView.layoutManager = currentLayoutManager

        val contactManager = ContactManagerImpl.getInstance()
        val contactList = contactManager.getContactList()
        val adapter = ContactAdapter(contactList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)


//        binding.changeButton.setOnClickListener {
//
//            currentLayoutManager = if (currentLayoutManager == linearLayoutManager) {
//                gridLayoutManager
//            } else {
//                linearLayoutManager
//            }
//            recyclerView.layoutManager = currentLayoutManager
//
//            adapter.notifyDataSetChanged()
//        }


        adapter.itemClick = object : ContactAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val detailFragment = ContactDetailFragment()
                val bundle = bundleOf()
                detailFragment.arguments = bundle
                                detailFragment.arguments = Bundle().apply {
                    bundleOf("ITEM_ID" to adapter.getContact(position).id)
                }

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, detailFragment)
                    .addToBackStack(null)
                    .commit()

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
