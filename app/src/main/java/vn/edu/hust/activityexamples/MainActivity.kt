package vn.edu.hust.activityexamples

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vn.edu.hust.activityexamples.Contact
import vn.edu.hust.activityexamples.ContactsAdapter
import vn.edu.hust.activityexamples.DetailActivity
import vn.edu.hust.activityexamples.R
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val contacts = listOf(
        Contact(1, "Josefina Lehner", "123-456-7890", "john.doe@gmail.com"),
        Contact(2, "Stuart Vandervort II", "987-654-3210", "jane.smith@gmail.com"),
        Contact(3,"Maddison Russel", "452-268-4578","maddison@gmail.com"),
        Contact(4,"Halie Morar II","123-456-7390", "haliecute@gmail.com"),
        Contact(5,"Karelle Simonis", "123-456-7840","karelle@gmail.com"),
        Contact(6,"Hannah Welch", "123-456-4578", "hannah@gmail.com"),
        Contact(7,"Fanny Frami", "324-145-6445","halffanny@gmail.com"),
        Contact(8,"Elfrieda Wisozk","124-988-2929","elfried@gmail.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = ContactsAdapter(this, contacts)

        // Registering the ListView for the context menu
        registerForContextMenu(listView)

        // Handling item click for opening the detail activity
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contacts[position]
            showDetailActivity(selectedContact)
        }
    }

    private fun showDetailActivity(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_CONTACT, contact as Serializable)
        startActivity(intent)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        val selectedContact = contacts[position]

        menu?.add(0, 0, 0, "Call")?.setOnMenuItemClickListener {
            showToast("Call: ${selectedContact.phoneNumber}")
            true
        }

        menu?.add(0, 1, 1, "Send SMS")?.setOnMenuItemClickListener {
            showToast("Send SMS to: ${selectedContact.phoneNumber}")
            true
        }

        menu?.add(0, 2, 2, "Send Email")?.setOnMenuItemClickListener {
            showToast("Send Email to: ${selectedContact.email}")
            true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
