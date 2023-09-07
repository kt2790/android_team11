package com.example.contactapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.adapter.ContactAdapter
import com.example.contactapp.databinding.FragmentContactListBinding
import com.example.contactapp.manager.ContactManagerImpl
import com.example.contactapp.ui.activity.MainActivity

class ContactListFragment : Fragment() {

    private var _binding: com.example.contactapp.databinding.FragmentContactListBinding? = null
    private val contactManager = ContactManagerImpl.getInstance()
    private val contactList = contactManager.getContactList()
    private val binding get() = _binding!!
    var adapter: ContactAdapter = ContactAdapter(contactList)
    lateinit var recyclerView: RecyclerView


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
        val dividerItemDecoration =
            DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        (requireActivity() as MainActivity).showToolbar()

//        linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//        gridLayoutManager = GridLayoutManager(requireActivity(), 2)
//        currentLayoutManager = linearLayoutManager
//        recyclerView.layoutManager = currentLayoutManager


        adapter = ContactAdapter(contactList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)


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

                val bundle = Bundle()
                bundle.putInt("ITEM_ID", adapter.getContact(position).id)
                detailFragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction()
                    //.replace(R.id.frameLayout, detailFragment)
                    //.addToBackStack(null)
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

}
