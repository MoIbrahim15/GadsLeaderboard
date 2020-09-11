package com.mo.aad.features.submission.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mo.aad.R
import kotlinx.android.synthetic.main.dialog_areyousure.view.*

class AreYouSureDialog : DialogFragment() {

    private lateinit var listener: AreYouSureDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(
                R.layout.dialog_areyousure,
                null
            )
            builder.setView(view)
            view.ivClose.setOnClickListener { dismiss() }
            view.btnYes.setOnClickListener {
                dismiss()
                listener.onYesClick()
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as AreYouSureDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() + " must implement NoticeDialogListener"))
        }
    }

    interface AreYouSureDialogListener {
        fun onYesClick()
    }
}