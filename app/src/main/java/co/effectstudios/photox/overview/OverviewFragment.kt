package co.effectstudios.photox.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.effectstudios.photox.databinding.OverViewFragmentBinding

//import co.effectstudios.photox.databinding.OverViewFragmentBinding

private const val TAG = "OverviewFragment"

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = OverViewFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(OverViewViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        binding.photosRecyclerView.adapter = PhotoListAdapter(PhotoListAdapter.OnClickListener {
            //viewModel.displayPhotoDetails(it)
        })

        viewModel.photoProperties.observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.i(TAG, "photos: ${it.toString()}")
            }
        })

        return binding.root
    }

}