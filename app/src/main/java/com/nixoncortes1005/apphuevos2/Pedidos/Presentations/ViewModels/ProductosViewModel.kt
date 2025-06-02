package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.ProductoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductosViewModel @Inject constructor(private val repository: ProductoRepository): ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos = _productos

    private val _productById = MutableStateFlow<Producto>(Producto(0,"Error",0))
    val productById = _productById

    fun getProductos(){
        viewModelScope.launch(Dispatchers.IO) {
            _productos.value = repository.getProductos()
        }
    }

    fun getProductById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            _productById.value = repository.getProductobyId(id)
        }
    }

    fun addProduct(prod: Producto){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProducto(prod)
            getProductos()
        }
    }

    fun updateProduct(prod: Producto){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProducto(prod)
        }
    }

    fun deleteProduct(prod: Producto){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProducto(prod)
            getProductos()
        }
    }
}