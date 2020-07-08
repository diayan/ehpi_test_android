package co.effectstudios.photox.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.effectstudios.photox.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this
        val photoData = DetailFragmentArgs.fromBundle(arguments!!).selectedPhoto
        val viewModelFactory = DetailViewModelFactory(photoData, application)

        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }

}