package com.example.webapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.webapp.R
import kotlinx.android.synthetic.main.fragment_pfd_view.*

private const val ARG_PATH = "pdfPath"

class PfdViewFragment : Fragment() {
    private var pdfPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pdfPath = it.getString(ARG_PATH)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pfd_view, container, false)
    }

    companion object {
        private const val TAG = "PdfViewFragment"

        @JvmStatic
        fun newInstance(param1: String) =
            PfdViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PATH, param1)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        webView1.settings.javaScriptEnabled = true
        webView1.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfPath")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                //for (i in 0 until requireActivity().supportFragmentManager.backStackEntryCount) {
                activity?.supportFragmentManager?.popBackStack()
                //}
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }
}