package com.example.registerland.fragments

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.registerland.R
import com.example.registerland.databinding.FragmentRegisterBinding


class Register : Fragment(), View.OnClickListener, View.OnFocusChangeListener {
    private lateinit var registerBinding: FragmentRegisterBinding
    //private lateinit var viewModel: WelcomeViewModel

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        registerBinding.nameEt.onFocusChangeListener = this
        registerBinding.surnameEt.onFocusChangeListener = this
        registerBinding.birthDayEt.onFocusChangeListener = this
        registerBinding.passwordEt.onFocusChangeListener = this
        registerBinding.cPasswordEt.onFocusChangeListener = this
        registerBinding.registerBtn.setOnClickListener(this)
        return registerBinding.root
    }

    private fun validateName(): Boolean {
        var errorMessage: String? = null
        val value: String = registerBinding.nameEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Name is required"
        }
        if (errorMessage != null) {
            registerBinding.nameTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateSurname(): Boolean {
        var errorMessage: String? = null
        val value: String = registerBinding.surnameEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Name is required"
        }
        if (errorMessage != null) {
            registerBinding.surnameTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateBirthDay(): Boolean {
        var errorMessage: String? = null
        val value: String = registerBinding.birthDayEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Date of birth is required"
        }
        if (errorMessage != null) {
            registerBinding.birthDayTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePassword(): Boolean {
        var errorMessage: String? = null
        val value = registerBinding.passwordEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password is required"
        } else if (value.length < 6) {
            errorMessage = "Password must be at least 6 characters long"
        }
        if (errorMessage != null) {
            registerBinding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val value = registerBinding.cPasswordEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Confirm password is required"
        } else if (value.length < 6) {
            errorMessage = "Confirm password must be at least 6 characters long"
        }
        if (errorMessage != null) {
            registerBinding.cPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePasswordAndConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val password = registerBinding.passwordEt.text.toString()
        val confirmPassword = registerBinding.cPasswordEt.text.toString()
        if (password != confirmPassword) {
            errorMessage = "Confirm password doesn't match with password"
        }
        if (errorMessage != null) {
            registerBinding.cPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.registerBtn) {
            onSubmit()
        }
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                R.id.nameEt -> {
                    if (hasFocus) {
                        if (registerBinding.nameTil.isErrorEnabled) {
                            registerBinding.nameTil.isErrorEnabled = false
                        }

                    } else {
                        validateName()
                    }
                }

                R.id.surnameEt -> {
                    if (hasFocus) {
                        if (registerBinding.surnameTil.isErrorEnabled) {
                            registerBinding.surnameTil.isErrorEnabled = false
                        }

                    } else {
                        validateSurname()
                    }

                }

                R.id.birth_dayEt -> {
                    if (hasFocus) {
                        if (registerBinding.birthDayTil.isErrorEnabled) {
                            registerBinding.birthDayTil.isErrorEnabled = false
                        }

                    } else {
                        validateBirthDay()
                    }
                }

                R.id.passwordEt -> {
                    if (hasFocus) {
                        if (registerBinding.passwordTil.isErrorEnabled) {
                            registerBinding.passwordTil.isErrorEnabled = false
                        }
                    } else {
                        if (validatePassword() && registerBinding.cPasswordEt.text!!.isNotEmpty() &&
                            validateConfirmPassword() && validatePasswordAndConfirmPassword()
                        ) {
                            if (registerBinding.cPasswordTil.isErrorEnabled) {
                                registerBinding.cPasswordTil.isErrorEnabled = false
                            }
                            registerBinding.cPasswordTil.apply {
                                setStartIconDrawable(R.drawable.check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }

                }

                R.id.cPasswordEt -> {
                    if (hasFocus) {
                        if (registerBinding.cPasswordTil.isErrorEnabled) {
                            registerBinding.cPasswordTil.isErrorEnabled = false
                        }

                    } else {
                        if (validateConfirmPassword() && validatePassword() && validatePasswordAndConfirmPassword()) {
                            if (registerBinding.passwordTil.isErrorEnabled) {
                                registerBinding.passwordTil.isErrorEnabled = false
                            }
                            registerBinding.cPasswordTil.apply {
                                setStartIconDrawable(R.drawable.check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }

                        }

                    }

                }
            }
        }
    }

    private fun onSubmit() {
        if (validate()) {
            val name = registerBinding.nameEt.text.toString()
            val action = RegisterDirections.actionRegisterToWelcome(name)
            findNavController().navigate(action)
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (!validateName()) isValid = false
        if (!validateSurname()) isValid = false
        if (!validateBirthDay()) isValid = false
        if (!validatePassword()) isValid = false
        if (!validateConfirmPassword()) isValid = false
        if (isValid && !validatePasswordAndConfirmPassword()) isValid = false
        return isValid
    }

}