package com.mytest.myapplication.ui.web

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.mytest.myapplication.R
import com.mytest.myapplication.databinding.FragmentWebBinding


class WebFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var binding: FragmentWebBinding
    private val args: WebFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebBinding.inflate(inflater, container, false)

        webView = binding.webview
//        webView.webViewClient = WebViewClient()
//
//        args.repo?.url?.let { webView.loadUrl(it) }




        return binding.root
    }




}