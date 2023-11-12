package vn.edu.hust.activityexamples

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTACT = "extra_contact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail)

        val avatarImageView: ImageView = findViewById(R.id.avatarImageView)
        val detailNameTextView: TextView = findViewById(R.id.detailNameTextView)
        val detailPhoneTextView: TextView = findViewById(R.id.detailPhoneTextView)
        val detailEmailTextView: TextView = findViewById(R.id.detailEmailTextView)

        val contact = intent.getSerializableExtra(EXTRA_CONTACT) as? Contact

        contact?.let {
            // Set avatar image (replace with your logic)
            avatarImageView.setImageBitmap(createAvatarBitmap(contact.name))


            // Set full name
            detailNameTextView.text = it.name

            val phoneNumber = "Phone number: " + it.phoneNumber
            // Set phone number
            detailPhoneTextView.text = phoneNumber

            val email = "Email: " + it.email

            // Set email
            detailEmailTextView.text = email
        } ?: run {
            // Handle the case where the contact is null (optional)
        }
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
