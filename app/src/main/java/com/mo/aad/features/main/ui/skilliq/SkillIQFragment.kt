package com.mo.aad.features.main.ui.skilliq

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mo.aad.R
import com.mo.aad.extensions.hide
import com.mo.aad.extensions.show
import com.mo.aad.features.main.ui.learning.LearningAdapter
import com.mo.aad.features.main.viewmodel.MainViewModel
import com.mo.aad.network.Status
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@FlowPreview
class SkillIQFragment : Fragment(R.layout.fragment_recycler) {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTopSkillIQUsers()

        viewModel.topSkillsLiveData.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    loading.show()
                }
                Status.SUCCESS -> {
                    loading.hide()
                    it.data?.let { items ->
                        recyclerView.show()
                        recyclerView.layoutManager = LinearLayoutManager(activity)
                        recyclerView.adapter = SkillIQAdapter(items = items)
                    }
                }
                Status.ERROR -> {
                    loading.hide()
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}