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

/*
    val displayAuthorNamee = Transformations.map(selectedPhoto) {
        app.applicationContext.getString(
            when (it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedPhoto) {
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(
                when(it.isRental) {
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }))
    }*/
}