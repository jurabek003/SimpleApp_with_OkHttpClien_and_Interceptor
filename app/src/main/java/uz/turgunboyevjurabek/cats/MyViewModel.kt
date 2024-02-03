package uz.turgunboyevjurabek.cats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.cats.madels.GetAllProductItem
import uz.turgunboyevjurabek.cats.network.ApiClient
import uz.turgunboyevjurabek.cats.utils.Resource

class MyViewModel():ViewModel() {
    val myRepozitory=MyRepozitory(ApiClient.api)

    private val myLiveData=MutableLiveData<Resource<ArrayList<GetAllProductItem>>>()

    fun getProduct():MutableLiveData<Resource<ArrayList<GetAllProductItem>>>{
        viewModelScope.launch {
            myLiveData.postValue(Resource.loading("Loading at MyViewModel"))
            try {
                val getData=async {
                    myRepozitory.GetAllItem()
                }.await()
                myLiveData.postValue(Resource.success(getData))
            }catch (e:Exception){
                myLiveData.postValue(Resource.error(e.message))
            }
        }
        return myLiveData
    }

}