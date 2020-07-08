package co.effectstudios.photox.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.effectstudios.photox.network.PhotoApi
import co.effectstudios.photox.network.PhotoData
import kotlinx.coroutines.*
import java.lang.Exception

enum class PhotoApiStatus { LOADING, ERROR, DONE }

class OverViewViewModel : ViewModel() {

    private val _status = MutableLiveData<PhotoApiStatus>()

    val status: LiveData<PhotoApiStatus>
        get() = _status

    private val _photoProperties = MutableLiveData<List<PhotoData>>()

    val photoProperties: LiveData<List<PhotoData>>
        get() = _photoProperties


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
            var getPhotodataDeferred = PhotoApi.retrofitService.getPhotos()

            try {

                //_status.value = PhotoApiStatus.LOADING
                val listResult = getPhotodataDeferred.await()
                _status.value = PhotoApiStatus.DONE
                _photoProperties.value = listResult

            } catch (e: Exception) {
                //_status.value = PhotoApiStatus.ERROR
                _photoProperties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}