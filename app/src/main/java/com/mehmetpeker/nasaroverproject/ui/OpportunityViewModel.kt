package com.mehmetpeker.nasaroverproject.ui

import androidx.lifecycle.ViewModel
import com.mehmetpeker.nasaroverproject.data.repository.NasaRoverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpportunityViewModel @Inject constructor(private val nasaRoverRepository: NasaRoverRepository) : ViewModel() {

}