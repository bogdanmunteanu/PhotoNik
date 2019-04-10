package com.death.photonik.ui.home.adapter

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.death.photonik.data.model.Photo
import com.death.photonik.databinding.SingleImageBinding
import com.death.photonik.ui.base.BaseItemViewModel
import com.death.photonik.ui.base.BaseViewHolder
import com.death.photonik.ui.detail.DetailActivity
import com.death.photonik.ui.home.HomeActivity
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import android.util.Pair as UtilPair
import timber.log.Timber

class PhotoAdapter(private val photos: ArrayList<Photo>, private val activity: HomeActivity) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            SingleImageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            PhotoViewModel()
        )
    }

    fun appendData(dummies: List<Photo>) {
        val oldCount = itemCount
        this.photos.addAll(dummies)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount in 1..(currentCount - 1))
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.setActivityInstance(activity)
        holder.bind(photos[position])
    }


    class PhotoViewHolder(binding: SingleImageBinding, viewModel: PhotoViewModel) :
        BaseViewHolder<SingleImageBinding, Photo, PhotoViewModel>(binding, viewModel) {
        lateinit var activity: HomeActivity

        fun setActivityInstance(activity:HomeActivity){
            this.activity = activity
        }

        override fun bindViewModel(viewModel: PhotoViewModel) {
            binding.viewModel = viewModel

            Picasso.get().load(viewModel.data!!.imageUrl)
                .resize(binding.poster.width, binding.poster.maxHeight)
                .centerCrop()
                .into(binding.poster, object : Callback {
                    override fun onSuccess() {
                        val drawable = binding.poster.drawable as BitmapDrawable
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

            binding.poster.setOnClickListener{
                val data = Gson().toJson(viewModel.data)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,binding.description, "title")
                binding.root.context.startActivity(Intent(binding.root.context, DetailActivity::class.java).putExtra(
                    "data", data
                ), options.toBundle())
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


    class PhotoViewModel : BaseItemViewModel<Photo>() {
        fun onItemClick() {
            Timber.e(data!!.id)
        }
    }

}