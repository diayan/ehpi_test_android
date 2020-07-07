package co.effectstudios.photox.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.effectstudios.photox.network.PhotoData

class DetailViewModel(photoData: PhotoData, app: Application) : AndroidViewModel(app){

    private val _selectedPhoto = MutableLiveData<PhotoData>()

    // The external LiveData for the selectedPhoto
    val selectedPhoto: LiveData<PhotoData>
        get() = _selectedPhoto

    init {
        _selectedPhoto.value = photoData
    }
}