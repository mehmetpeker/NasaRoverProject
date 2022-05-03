package com.mehmetpeker.nasaroverproject.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetpeker.nasaroverproject.R
import com.mehmetpeker.nasaroverproject.base.BaseFragment
import com.mehmetpeker.nasaroverproject.data.model.Photo
import com.mehmetpeker.nasaroverproject.databinding.FragmentSpiritBinding
import com.mehmetpeker.nasaroverproject.ui.SpiritViewModel
import com.mehmetpeker.nasaroverproject.ui.adapter.PhotoLoadStateAdapter
import com.mehmetpeker.nasaroverproject.ui.adapter.RoverPhotoItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SpiritFragment : BaseFragment<FragmentSpiritBinding>(FragmentSpiritBinding::inflate) {

    private val spiritViewModel:SpiritViewModel by viewModels()
    private var adapter:RoverPhotoItemAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
        setupPhotoList()
    }
    private fun setupRecyclerView() {
        if(adapter == null){
            adapter = RoverPhotoItemAdapter {

            }
        }
        binding.rvSpirit.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSpirit.adapter = adapter?.withLoadStateHeaderAndFooter(
            header = PhotoLoadStateAdapter { adapter?.retry() },
            footer = PhotoLoadStateAdapter { adapter?.retry() }
        )
    }
    private fun setupPhotoList(){
        lifecycleScope.launchWhenStarted {
            spiritViewModel.getSpirithoto().collectLatest {
                adapter?.submitData(it)
            }
        }
    }
    private fun filterByCamera(){

    }

    private fun showPopupDialog(photo: Photo) {

    }

    private fun showProgressDialog() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressDialog() {
        binding.progressBar.visibility = View.GONE
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_spirit,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}