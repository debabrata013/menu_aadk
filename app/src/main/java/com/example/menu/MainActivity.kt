package com.example.menu

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.menu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedItemPosition: Int = -1  // Store selected item position

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the Toolbar as the ActionBar
        setSupportActionBar(binding.toolbar2)

        // Set up the ListView
        val names = arrayOf("Debabrat", "Mahakal", "Bhero ji")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        binding.mylist.adapter = arrayAdapter

        // Register ListView for Context Menu
        registerForContextMenu(binding.mylist)

        // Handle item click to open context menu
        binding.mylist.onItemClickListener = AdapterView.OnItemClickListener { _, view, position, _ ->
            selectedItemPosition = position // Store selected position
            openContextMenu(view) // Open context menu when an item is clicked
        }
    }

    // Inflate the options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    // Handle menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit -> {
                Toast.makeText(this, "Edit Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.call -> {
                Toast.makeText(this, "Call Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.message -> {
                Toast.makeText(this, "Message Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Create a Context Menu for ListView
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.mylist) { // ✅ Correct ID check
            menuInflater.inflate(R.menu.contextmenu, menu)
        }
    }

    // Handle Context Menu selection
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val names = arrayOf("Debabrat", "Mahakal", "Bhero ji")
        val selectedName = if (selectedItemPosition >= 0) names[selectedItemPosition] else "Unknown"

        return when (item.itemId) {
            R.id.call -> {
                Toast.makeText(this, "Calling $selectedName", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.message -> {
                Toast.makeText(this, "Messaging $selectedName", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
