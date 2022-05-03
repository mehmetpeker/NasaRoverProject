package com.mehmetpeker.nasaroverproject.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetpeker.nasaroverproject.R
import com.mehmetpeker.nasaroverproject.base.BaseFragment
import com.mehmetpeker.nasaroverproject.data.model.Photo
import com.mehmetpeker.nasaroverproject.data.model.RoverPhotoResponseModel
import com.mehmetpeker.nasaroverproject.databinding.FragmentCuriosityBinding
import com.mehmetpeker.nasaroverproject.ui.CuriosityViewModel
import com.mehmetpeker.nasaroverproject.ui.adapter.PhotoLoadStateAdapter
import com.mehmetpeker.nasaroverproject.ui.adapter.RoverPhotoItemAdapter
import com.mehmetpeker.nasaroverproject.util.Constants
import com.mehmetpeker.nasaroverproject.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CuriosityFragment :
    BaseFragment<FragmentCuriosityBinding>(FragmentCuriosityBinding::inflate) {


    private val curiosityViewModel: CuriosityViewModel by viewModels<CuriosityViewModel>()
    private var adapter: RoverPhotoItemAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        binding.rvCuriosity.layoutManager = LinearLayoutManager(requireContext())

        binding.rvCuriosity.adapter = adapter?.withLoadStateHeaderAndFooter(
            header = PhotoLoadStateAdapter { adapter?.retry() },
            footer = PhotoLoadStateAdapter { adapter?.retry() }
        )
    }

    private fun setupPhotoList(camera: String = Constants.CAMERA_DEFAULT) {
        lifecycleScope.launchWhenStarted{
            curiosityViewModel.getCuriosityPhoto(camera).collectLatest {
                adapter?.submitData(it)
            }
        }

    }

    private fun filterByCamera(camera: String) {
        adapter?.refresh()
        lifecycleScope.launch {
            curiosityViewModel.getCuriosityPhoto(camera).collectLatest {
                Log.d("CustomLog",it.toString())
                adapter?.submitData(it)
            }
        }
    }

    private fun showPopupDialog(photo: Photo) {

    }

    private fun changeSelectedCamera(camera:String){
        curiosityViewModel.selectedCamera = camera
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_curiosity, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val type = when (item.itemId) {
            R.id.item_camera_fhaz -> Constants.CAMERA_FHAZ
            R.id.item_camera_rhaz -> Constants.CAMERA_RHAZ
            R.id.item_camera_mast -> Constants.CAMERA_MAST
            R.id.item_camera_chemcam -> Constants.CAMERA_CHEMCAM
            R.id.item_camera_mahli -> Constants.CAMERA_MAHLI
            R.id.item_camera_mardi -> Constants.CAMERA_MARDI
            R.id.item_camera_navcam -> Constants.CAMERA_NAVCAM
            else -> Constants.CAMERA_DEFAULT
        }
        if (curiosityViewModel.selectedCamera == type)
            return false
        changeSelectedCamera(camera = type)
        filterByCamera(type)
        return true

    }


}