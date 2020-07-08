package co.effectstudios.photox

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.effectstudios.photox.network.PhotoData
import co.effectstudios.photox.overview.PhotoApiStatus
import co.effectstudios.photox.overview.PhotoListAdapter
import com.squareup.picasso.Picasso

//hide recyclerview when there are no photos
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PhotoData>?) {
    val adapter = recyclerView.adapter as PhotoListAdapter
    adapter.submitList(data)
}

//use picasso to load images by url into the imageView
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Picasso.get()
            .load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .fit()
            .centerCrop()
            .into(imageView)
    }
}

@BindingAdapter("photoApiStatus")
fun bindStatus(statusImageView: ImageView, status: PhotoApiStatus?) {
    when (status) {
        PhotoApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PhotoApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        PhotoApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}