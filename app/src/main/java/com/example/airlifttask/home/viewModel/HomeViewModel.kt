package com.example.airlifttask.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airlifttask.home.fragment.CategoryTabFragment
import com.example.airlifttask.home.model.CategoryTab
import com.example.airlifttask.home.model.Product
import com.example.airlifttask.network.ApiProvider
import kotlinx.coroutines.*

class HomeViewModel(): ViewModel() {

    val productList= MutableLiveData<ArrayList<Product>>()
    val errorMessage=MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    var job: Job? = null
    private val exceptionHandler= CoroutineExceptionHandler{_,throwable->

    }

    fun getProducts(){
        isLoading.value=true
        job= CoroutineScope(Dispatchers.IO).launch {
            val response=ApiProvider.getInstance().getProductList()

            if(response.isSuccessful){

                response.body()?.let {
                    productList.postValue(it)

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