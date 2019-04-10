package com.death.photonik.ui.detail

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import com.death.photonik.R
import com.death.photonik.data.model.Photo
import com.death.photonik.databinding.ActivityDetailBinding
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)

        val photo = Gson().fromJson<Photo>(intent.getStringExtra("data"), Photo::class.java)

        title = photo.name

        photo?.let {
            binding.description.text = photo.description
            binding.loader.visibility = View.VISIBLE
            Picasso.get().load(photo.imageUrl)
                .into(binding.photo, object : Callback {
                    override fun onSuccess() {
                        binding.loader.visibility = View.GONE
                        val drawable = binding.photo.drawable as BitmapDrawable
                        val bitmap = drawable.bitmap
                        Palette.from(bitmap).generate { palette ->
                            val color =
                                palette!!.getDominantColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        android.R.color.black
                                    )
                                )
                            binding.description.setBackgroundColor(color)
                            binding.description.setTextColor(getComplimentColor(color))
                        }
                    }

                    override fun onError(e: Exception?) {
                        e!!.printStackTrace()
                    }
                })
        }
    }

    fun getComplimentColor(color: Int): Int {
        val alpha = Color.alpha(color)
        var red = Color.red(color)
        var blue = Color.blue(color)
        var green = Color.green(color)

        red = red.inv() and 0xff
        blue = blue.inv() and 0xff
        green = green.inv() and 0xff

        return Color.argb(alpha, red, green, blue)
    }
}
