package vn.edu.hust.activityexamples

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import android.widget.ImageView

class ContactsAdapter(private val context: Context, private val contacts: List<Contact>) : BaseAdapter() {

    override fun getCount(): Int {
        return contacts.size
    }

    override fun getItem(position: Int): Any {
        return contacts[position]
    }

    override fun getItemId(position: Int): Long {
        return contacts[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        val contact = getItem(position) as Contact

        val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)

        // Set avatar image with the first letter of the name dynamically
        avatarImageView.setImageBitmap(createAvatarBitmap(contact.name))

        // Set full name
        nameTextView.text = contact.name

        return view
    }

    private fun createAvatarBitmap(name: String): Bitmap {
        val size = 48
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()

        // Draw a circle background
        paint.color = Color.GRAY
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

        // Draw the first letter of the name
        paint.color = Color.WHITE
        paint.textSize = 24f
        paint.textAlign = Paint.Align.CENTER
        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (paint.descent() + paint.ascent()) / 2)

        // Use uppercase() instead of deprecated toUpperCase()
        canvas.drawText(name.substring(0, 1).uppercase(), xPos.toFloat(), yPos, paint)

        return bitmap
    }
}
