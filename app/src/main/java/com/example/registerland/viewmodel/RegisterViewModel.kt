package com.example.registerland.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    //name
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name
    /*//surname
    private val _surname = MutableLiveData<String>()
    val surname:LiveData<String>
        get() = _surname
    //password
    private val _password = MutableLiveData<String>()
    val password:LiveData<String>
        get() = _password
    //Confirm password
    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword:LiveData<String>
        get() = _confirmPassword*/


}