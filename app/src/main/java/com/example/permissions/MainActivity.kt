package com.example.permissions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {
    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startActivity(Intent(this, CameraActivity::class.java))
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val contactsPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startActivity(Intent(this, ContactsActivity::class.java))
            } else {
                Toast.makeText(this, "Contacts permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<Button>(R.id.button_camera).setOnClickListener {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        findViewById<Button>(R.id.button_contacts).setOnClickListener {
            contactsPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
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