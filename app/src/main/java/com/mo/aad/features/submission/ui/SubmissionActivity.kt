package com.mo.aad.features.submission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mo.aad.R
import com.mo.aad.extensions.hide
import com.mo.aad.extensions.show
import com.mo.aad.features.submission.viewmodel.SubmissionViewModel
import com.mo.aad.network.Status
import kotlinx.android.synthetic.main.activity_submission.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class SubmissionActivity : AppCompatActivity(R.layout.activity_submission),
    AreYouSureDialog.AreYouSureDialogListener {

    private val viewModel: SubmissionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        btnSubmit.setOnClickListener {
            showSubmissionDialog()
        }
    }

    override fun onYesClick() {
        viewModel.submissionLiveData.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    loading.show()
                }
                Status.SUCCESS -> {
                    loading.hide()
                    val successDialog = SuccessDialog()
                    successDialog.show(supportFragmentManager, "SuccessDialog")
                }
                Status.ERROR -> {
                    loading.hide()
                    val failureDialog = FailureDialog()
                    failureDialog.show(supportFragmentManager, "FailureDialog")
                }
            }
        })
        viewModel.projectSubmission(
            etFirstName.text.toString(),
            etSecondName.text.toString(),
            etEmail.text.toString(),
            etGithubLink.text.toString()
        )
    }

    private fun showSubmissionDialog() {
        val dialog = AreYouSureDialog()
        dialog.show(supportFragmentManager, "AreYouSureDialog")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}