package com.mo.aad.features.main.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mo.aad.features.main.ui.learning.TopLearnerFragment
import com.mo.aad.features.main.ui.skilliq.SkillIQFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class PagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) {
            "Learning Leaders"
        } else {
            "Skill IQ Leaders"
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            TopLearnerFragment()
        } else {
            SkillIQFragment()
        }
    }
}