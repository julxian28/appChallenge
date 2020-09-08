package pakultie.julxian.simpleproject.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import pakultie.julxian.simpleproject.data.repository.MainRepository
import pakultie.julxian.simpleproject.utils.ResourceClass


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(ResourceClass.loading(data = null))
        try {
            emit(ResourceClass.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(ResourceClass.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}