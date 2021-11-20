package com.example.airlifttask.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airlifttask.home.model.Product
import com.example.airlifttask.network.ApiProvider
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import java.lang.Exception

class HomeViewModel(): ViewModel() {

    val productList= MutableLiveData<ArrayList<Product>>()
    val errorMessage=MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    var job: Job? = null
    private val exceptionHandler= CoroutineExceptionHandler{_,throwable->

    }

    fun getProducts(){
        isLoading.postValue(true)

        job= viewModelScope.launch(Dispatchers.IO) {

            val response=ApiProvider.getInstance().getProductList()

            if(response.isSuccessful){

                response.body()?.let {
                    viewModelScope.launch(Dispatchers.Main) {
                        productList.postValue(it)
                        isLoading.postValue(false)
                    }
                }

            }else{
                onError("Error : ${response.message()} ")
            }
        }
    }

    private fun onError(message:String){
        errorMessage.value=message
        isLoading.value=false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}