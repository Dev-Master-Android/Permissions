package com.example.permissions

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.permissions.databinding.ActivityContactsBinding


class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private val contactsAdapter = ContactsAdapter(getContacts())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = contactsAdapter
    }

    private fun getContacts(): List<Contact> {
        return List(20) { index ->
            Contact("Contact #$index", "555-123-$index")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_exit -> {
                finishAffinity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
