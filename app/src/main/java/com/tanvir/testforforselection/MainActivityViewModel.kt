package com.tanvir.testforforselection



import androidx.lifecycle.ViewModel
import com.tanvir.testforforselection.Repository.MainActivityRepository


class MainActivityViewModel (val repository: MainActivityRepository) : ViewModel(){


    val jobsInfo = repository.responseLiveData




}