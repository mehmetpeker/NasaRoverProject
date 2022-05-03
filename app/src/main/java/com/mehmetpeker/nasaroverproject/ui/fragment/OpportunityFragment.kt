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
import com.mehmetpeker.nasaroverproject.databinding.FragmentOpportunityBinding
import com.mehmetpeker.nasaroverproject.ui.OpportunityViewModel
import com.mehmetpeker.nasaroverproject.ui.adapter.PhotoLoadStateAdapter
import com.mehmetpeker.nasaroverproject.ui.adapter.RoverPhotoItemAdapter
import com.mehmetpeker.nasaroverproject.ui.dialog.RoverPopupDialog
import com.mehmetpeker.nasaroverproject.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OpportunityFragment : BaseFragment<FragmentOpportunityBinding>(FragmentOpportunityBinding::inflate) {

    private val opportunityViewModel:OpportunityViewModel by viewModels()
    private var adapter:RoverPhotoItemAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        setupRecyclerView()
        setupPhotoList()


    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            adapter = RoverPhotoItemAdapter {
                showPopupDialog(it)
            }
        }

        binding.rvOpportunity.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOpportunity.adapter = adapter?.withLoadStateHeaderAndFooter(
            header = PhotoLoadStateAdapter { adapter?.retry() },
            footer = PhotoLoadStateAdapter { adapter?.retry() }
        )
    }

    private fun setupPhotoList() {
        lifecycleScope.launchWhenStarted{
            opportunityViewModel.getOpportunityPhoto().collectLatest {
                adapter?.submitData(it)
            }
        }

    }

    private fun filterByCamera() {

        lifecycleScope.launch {
            opportunityViewModel.getOpportunityPhoto().collectLatest {
                adapter?.refresh()
                adapter?.submitData(it)
                if (adapter?.itemCount!! > 0)
                adapter?.peek(0)
            }
        }
    }

    private fun showPopupDialog(photo: Photo) {
        val dialog = RoverPopupDialog(requireActivity(),photo)
        dialog.showDialog()
    }

    private fun changeSelectedCamera(camera:String){
        opportunityViewModel.selectedCamera = camera
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_opprtunity,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val type = when (item.itemId) {
            R.id.item_camera_fhaz -> Constants.CAMERA_FHAZ
            R.id.item_camera_rhaz -> Constants.CAMERA_RHAZ
            R.id.item_camera_navcam -> Constants.CAMERA_NAVCAM
            R.id.item_camera_pancam -> Constants.CAMERA_PANCAM
            R.id.item_camera_minites -> Constants.CAMERA_MINITES
            else -> Constants.CAMERA_DEFAULT
        }
        if (opportunityViewModel.selectedCamera == type)
            return false
        changeSelectedCamera(camera = type)
        filterByCamera()
        return true
    }

}