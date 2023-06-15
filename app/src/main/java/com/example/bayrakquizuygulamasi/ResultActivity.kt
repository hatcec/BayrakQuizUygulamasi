package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bayrakquizuygulamasi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dogruSayac=intent.getIntExtra("dogruSayac",0)
        binding.textViewSonuc.text="$dogruSayac Doğru   ${5-dogruSayac} Yanlış"
        binding.textViewBasari.text="%${(dogruSayac*100)/5} Başarı"
        binding.buttonTekrarDene.setOnClickListener {
            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }
    }
}