package com.mehmetpeker.nasaroverproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mehmetpeker.nasaroverproject.R
import com.mehmetpeker.nasaroverproject.base.BaseFragment
import com.mehmetpeker.nasaroverproject.databinding.FragmentCuriosityBinding
import com.mehmetpeker.nasaroverproject.ui.CuriosityViewModel
import javax.inject.Inject

class CuriosityFragment : BaseFragment<FragmentCuriosityBinding>(FragmentCuriosityBinding::inflate) {

    private val curiosityViewModel: CuriosityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}