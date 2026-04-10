package com.example.applogin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applogin.model.Usuario
import com.example.applogin.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {


    private val apiService = ApiClient.instance


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var nome = findViewById<EditText>(R.id.etNome)
        var email = findViewById<EditText>(R.id.etEmail)
        var telefone = findViewById<EditText>(R.id.etTelefone)
        var cpf = findViewById<EditText>(R.id.etCpf)
        var senha = findViewById<EditText>(R.id.etSenha)
        var senhaConfirmar = findViewById<EditText>(R.id.etConfirmarSenha)
        var btnCadastrar = findViewById<Button>(R.id.btnCadastrarLogin)

        btnCadastrar.setOnClickListener {
            var usuario = Usuario (
                nome = nome.text.toString(),
                cpf = cpf.text.toString(),
                email = email.text.toString(),
                telefone = telefone.text.toString(),
                senha = senha.text.toString()
            )
            cadastroUsuario(usuario)

        }

    }
    fun cadastroUsuario(usuario: Usuario) {
        apiService.cadastrarUsuario(usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(
                call: Call<Usuario?>,
                response: Response<Usuario?>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@CadastroActivity,
                        "Cadastro realizado com sucesso",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                Toast.makeText(this@CadastroActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}