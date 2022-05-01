package com.mehmetpeker.nasaroverproject.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mehmetpeker.nasaroverproject.base.BaseFragment
import com.mehmetpeker.nasaroverproject.databinding.FragmentSpiritBinding
import com.mehmetpeker.nasaroverproject.ui.SpiritViewModel

class SpiritFragment : BaseFragment<FragmentSpiritBinding>(FragmentSpiritBinding::inflate) {

    private val spiritViewModel:SpiritViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}