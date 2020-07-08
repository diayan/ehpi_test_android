package co.effectstudios.photox.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        binding.photosRecyclerView.adapter = PhotoListAdapter(PhotoListAdapter.OnClickListener {
            viewModel.displayPhotoDetails(it)
        })

        viewModel.selectedPhoto.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetails(it))
                viewModel.displayPhotoDetailsComplete()
            }
        })
        return binding.root
    }
}