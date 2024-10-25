package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menghubungkan komponen UI dengan variabel
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // Listener untuk tombol login
        btnLogin.setOnClickListener {
            // Lakukan validasi login sebelum berpindah ke ListFoodActivity
            performLogin()
        }

        // Listener untuk link Register
        findViewById<TextView>(R.id.Register).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun performLogin() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        // Logika validasi sederhana
        if (username.isNotEmpty() && password.isNotEmpty()) {
            // Jika login berhasil, arahkan ke ListFoodActivity (bisa diganti dengan halaman lain)
            val intent = Intent(this, ListFoodActivity::class.java)
            startActivity(intent)
            finish() // Menutup MainActivity setelah login berhasil
        } else {
            // Tampilkan pesan error jika input kosong
            Toast.makeText(this, "Harap masukkan username dan password", Toast.LENGTH_SHORT).show()
        }
    }
}
