package com.mehmetpeker.nasaroverproject.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mehmetpeker.nasaroverproject.base.BaseFragment
import com.mehmetpeker.nasaroverproject.databinding.FragmentOpportunityBinding
import com.mehmetpeker.nasaroverproject.ui.OpportunityViewModel

class OpportunityFragment : BaseFragment<FragmentOpportunityBinding>(FragmentOpportunityBinding::inflate) {

    private val opportunityViewModel:OpportunityViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}