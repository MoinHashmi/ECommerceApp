package com.example.airlifttask.home.viewModel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airlifttask.home.fragment.CategoryFragment
import com.example.airlifttask.home.model.CategoryTab
import com.example.airlifttask.home.model.Product
import com.example.airlifttask.network.ApiProvider
import kotlinx.coroutines.*

class HomeViewModel(): ViewModel() {

    val errorMessage=MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val tabList=MutableLiveData<ArrayList<CategoryTab>>()
    var job: Job? = null

    fun getProducts(){
        isLoading.postValue(true)


        job= viewModelScope.launch(Dispatchers.IO) {

            try {
                val response=ApiProvider.getInstance().getProductList()

                if(response.isSuccessful){

                    response.body()?.let {
                        val tabLst=getTabData(it)
                        viewModelScope.launch(Dispatchers.Main) {
                            tabList.postValue(tabLst)
                            isLoading.postValue(false)
                        }
                    }

                }else{
                    Log.i("INTERNET_EX","${response.message()}")
                    onError("Error : ${response.message()} ")
                }
            }catch (e:Exception){
                Log.i("INTERNET_EX","${e.localizedMessage}")

                onError("Error : ${e.localizedMessage} ")
            }

        }
    }

    private fun onError(message:String){
        viewModelScope.launch(Dispatchers.Main) {
            errorMessage.postValue(message)
        }
    }

    private fun getTabData(productList:ArrayList<Product>): ArrayList<CategoryTab> {
        val map=HashMap<String,ArrayList<Product>>()
        val lst=ArrayList<CategoryTab>()
        productList.forEach {
            val prodList= if (map.containsKey(it.category)) map.get(it.category) else ArrayList<Product>()
            prodList?.add(it)
            prodList?.let { lst -> map.put(it.category, lst) }
        }

        map.forEach{
            val fragment=CategoryFragment()
            val bundle=Bundle()
            bundle.putSerializable("prodList",it.value)
            fragment.arguments=bundle
            lst.add(CategoryTab(it.key,fragment))
        }

        return lst
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}