package com.example.applogin.service

import com.example.applogin.model.Login
import com.example.applogin.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/cadastro")
    fun cadastrarUsuario(
        @Body usuario : Usuario
    ) : Call<Usuario>

    @POST ("/autenticar")
    fun autenticar(
        @Body login: Login
    ) : Call<Boolean>

}