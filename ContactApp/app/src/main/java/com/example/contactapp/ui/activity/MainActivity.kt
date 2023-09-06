package com.example.contactapp.ui.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.contactapp.R
import com.example.contactapp.adapter.ViewPagerFragmentStateAdapter
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.manager.ContactManagerImpl
import com.example.contactapp.model.Contact
import com.example.contactapp.ui.activity.MainActivity.Companion.PERMISSION_REQUEST_CODE
import com.example.contactapp.ui.dialog.AddContactDialogFragment
import com.example.contactapp.ui.fragment.ContactDetailFragment
import com.example.contactapp.ui.fragment.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

var count = 0

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentManager = supportFragmentManager
    private lateinit var transaction: FragmentTransaction

    companion object {
        const val PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, HomeFragment())
        transaction.commit()

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.apply {
            title = ""
            setVisible(true)
        }

        val status = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
        Log.d("test", status.toString())

        if (status == PackageManager.PERMISSION_GRANTED) {
            getContactsList()
        } else {
            if (shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS")) {
                //
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>("android.permission.READ_CONTACTS"),
                    100
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_setting -> Toast.makeText(this, "ListType", Toast.LENGTH_LONG).show()
            R.id.grid_setting -> Toast.makeText(this, "GridType", Toast.LENGTH_LONG).show()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContactsList()
        }
    }

    private fun getContactsList() {
        val contacts = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val contactManagerImpl = ContactManagerImpl.getInstance()
        contacts?.let {
            while (it.moveToNext()) {
                val name =
                    contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactManagerImpl.createContact(name, number, "aa@ss.com", 0)
            }
        }
        contacts?.close()
    }
}