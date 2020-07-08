package co.effectstudios.photox.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.effectstudios.photox.network.PhotoData
import co.effectstudios.photox.databinding.ListViewItemBinding

class PhotoListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<PhotoData, PhotoListAdapter.PhotoDataViewHolder>(DiffCallback) {

    class PhotoDataViewHolder(private var binding: ListViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoData) {
            binding.photos = photo
            //binding.authorTextView.text = photo.author
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoDataViewHolder {
        return PhotoDataViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoDataViewHolder, position: Int) {
        val photoItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(photoItem)
        }
        holder.bind(photoItem)
    }

    //allows recyclerview to determine which items have change and update accordingly
    object DiffCallback : DiffUtil.ItemCallback<PhotoData>() {
        override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem.id == newItem.id
        }
    }

    //custom listener to listen to recyclerview items clicks
    class OnClickListener(val clickListener: (photo: PhotoData) -> Unit) {
        fun onClick(photo: PhotoData) = clickListener(photo)
    }
}