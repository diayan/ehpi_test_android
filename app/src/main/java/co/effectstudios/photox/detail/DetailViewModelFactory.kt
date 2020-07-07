package co.effectstudios.photox.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.effectstudios.photox.network.PhotoData

//Simple ViewModel factory that provides the photo data and context to the ViewModel.

class DetailViewModelFactory(
        private val photoData: PhotoData,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(photoData, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
