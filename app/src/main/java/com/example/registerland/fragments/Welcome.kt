package com.example.registerland.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.registerland.R
import com.example.registerland.databinding.FragmentWelcomeBinding
import com.example.registerland.viewmodel.WelcomeViewModel
import com.example.registerland.viewmodel.WelcomeViewModelFactory


class Welcome : Fragment() {
    private lateinit var welcomeBinding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel
    private lateinit var viewModelFactory: WelcomeViewModelFactory

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        welcomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        viewModelFactory =
            WelcomeViewModelFactory(WelcomeArgs.fromBundle(requireArguments()).nameUser)
        viewModel = ViewModelProvider(this, viewModelFactory)[WelcomeViewModel::class.java]

        welcomeBinding.welcomeBtn.setOnClickListener {
            val dialogCreate = layoutInflater.inflate(R.layout.dialog_window, null)
            val myDialog = Dialog(requireActivity())
            myDialog.setContentView(dialogCreate)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val welcomeModal = dialogCreate.findViewById<TextView>(R.id.welcome_modal)
            welcomeModal.text = "${viewModel.name.value}, " +
                    "welcome to the motherland, we ware glad to have you here with us, " +
                    "feel free to ask questions, enjoy your stay!!"
            myDialog.show()
        }
        return welcomeBinding.root
    }

}