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

private const val TAG = "OverviewFragment"

class OverviewFragment : Fragment() {

    private val viewModel: OverViewViewModel by lazy {
        ViewModelProvider(this).get(OverViewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = OverViewFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel


        binding.photosRecyclerView.adapter = PhotoListAdapter(PhotoListAdapter.OnClickListener {
            //viewModel.displayPhotoDetails(it)
        })
        viewModel.photoProperties.observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.i(TAG, "photos: ${it}")

            }
        })

        return binding.root
    }
}