package com.andregomesolievira.mymoviesshelf.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.andregomesolievira.mymoviesshelf.database.MovieDatabase
import com.andregomesolievira.mymoviesshelf.databinding.CollectionFragmentBinding
import com.andregomesolievira.mymoviesshelf.overview.PosterGridAdapter

class CollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val dataSource = MovieDatabase.getInstance(application).movieDatabaseDao
        val binding = CollectionFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val viewModelFactory = CollectionViewModelFactory(dataSource, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(CollectionViewModel::class.java)

        // Sets the adapter of the posterGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        binding.collectionPostersGrid.adapter = PosterGridAdapter(null)

        return binding.root
    }
}
