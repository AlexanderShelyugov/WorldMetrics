package ru.alexander.worldmetrics.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.ToastHelper

class AboutMeActivity : AppCompatActivity(R.layout.about_me_activity) {
    private companion object {
        val CONTACTS = mapOf(
            R.id.cl_my_website to ContactItem(
                R.drawable.ic_round_public_24,
                R.string.my_website,
                R.string.my_website_url,
            ),
            R.id.cl_my_linkedin to ContactItem(
                R.drawable.ic_linkedin,
                R.string.my_linkedin,
                R.string.my_linkedin_url,
            ),
            R.id.cl_my_upwork to ContactItem(
                R.drawable.ic_upwork,
                R.string.my_upwork,
                R.string.my_upwork_url,
            ),
            R.id.cl_my_peopleperhour to ContactItem(
                R.drawable.ic_peopleperhour,
                R.string.my_peopleperhour,
                R.string.my_peopleperhour_url,
            ),
        )
    }

    private lateinit var clipboardManager: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        CONTACTS.forEach { (id, contact) ->
            val container = findViewById<View>(id)!!
            container.findViewById<ImageView>(R.id.iv_icon)
                .setImageResource(contact.icon)
            val name = getString(contact.name)
            val url = getString(contact.url)
            container.findViewById<TextView>(R.id.tv_contact_name).text = name
            container.findViewById<ImageButton>(R.id.ib_copy).setOnClickListener {
                val clip = ClipData.newPlainText(name, url)
                clipboardManager.setPrimaryClip(clip)
                val message = getString(R.string.copied_format, name)
                ToastHelper.show(message)
            }
            container.setOnClickListener {
                val intent = Intent(ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
        Glide.with(this)
            .load(R.raw.snoop_dogg_dance)
            .into(findViewById(R.id.iv_snoop_dogg))
    }
}

private data class ContactItem(
    val icon: Int,
    val name: Int,
    val url: Int,
)