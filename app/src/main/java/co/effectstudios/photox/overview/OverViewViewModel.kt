package co.effectstudios.photox.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.effectstudios.photox.network.PhotoApi
import co.effectstudios.photox.network.PhotoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class PhotoApiStatus { LOADING, ERROR, DONE }

class OverViewViewModel : ViewModel() {

    private val _status = MutableLiveData<PhotoApiStatus>()

    val status: LiveData<PhotoApiStatus>
        get() = _status

    private val _photoProperties = MutableLiveData<List<PhotoData>>()

    val photoProperties: LiveData<List<PhotoData>>
        get() = _photoProperties

    private val _selectedPhoto = MutableLiveData<PhotoData>()

    val selectedPhoto: LiveData<PhotoData>
        get() = _selectedPhoto



    //coroutine scope using job to be able to cancel
    private var viewModelJob = Job()

    //coroutine runs using the main ui dispatcher
    private val coroutineScope = CoroutineScope((viewModelJob + Dispatchers.Main))

    //get photo data
    init {
        getPhotoData()
    }

    //get photo data from photo Api retrofit service
    private fun getPhotoData() {
        coroutineScope.launch {
            val getPhotodataDeferred = PhotoApi.retrofitService.getPhotos()

            try {
                _status.value = PhotoApiStatus.LOADING
                val listResult = getPhotodataDeferred.await()
                _status.value = PhotoApiStatus.DONE
                _photoProperties.value = listResult

            } catch (e: Exception) {
                _status.value = PhotoApiStatus.ERROR
                _photoProperties.value = ArrayList()
            }
        }
    }

    //display photoDetails
    fun displayPhotoDetails(photoData: PhotoData) {
        _selectedPhoto.value = photoData
    }

    //set displayPhotoDetails to null after navigation is complete
    fun displayPhotoDetailsComplete() {
        _selectedPhoto.value = null
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}