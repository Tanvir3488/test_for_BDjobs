package com.tanvir.testforforselection.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanvir.testforforselection.MainActivityViewModel
import java.lang.IllegalArgumentException


class MainActivityViewModelFactory(private  val repository : MainActivityRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown View Model Class")
    }

    
}