package com.example.contactapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.contactapp.R
import com.example.contactapp.adapter.ViewPagerFragmentStateAdapter
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.ui.dialog.AddContactDialogFragment
import com.example.contactapp.ui.fragment.ContactDetailFragment
import com.example.contactapp.ui.fragment.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val fragmentManager = supportFragmentManager
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, ContactDetailFragment())
        transaction.commit()

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.apply {
            title = "Contact"
            setVisible(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miSetting -> Toast.makeText(this, "Setting", Toast.LENGTH_LONG).show()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}