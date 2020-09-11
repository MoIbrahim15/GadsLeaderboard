package com.mo.aad.features.submission.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mo.aad.R

class FailureDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(
                R.layout.dialog_failure,
                null
            )
            builder.setView(view)
            builder.setCancelable(true)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}