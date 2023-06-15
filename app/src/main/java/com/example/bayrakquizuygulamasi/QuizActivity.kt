package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bayrakquizuygulamasi.databinding.ActivityMainBinding
import com.example.bayrakquizuygulamasi.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuizBinding
    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var vt:VeriTabaniYardimcisi
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private var soruSayac=0
    private var dogruSayac=0
    private var yanlisSayac=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt=VeriTabaniYardimcisi(this)
        sorular=Bayrakdao().rastgele5BayrakSorusuGetir(vt)

        soruYukle()

        binding.buttona.setOnClickListener {
            dogruKontrol(binding.buttona)
            soruSayacKontrol()

        }
        binding.buttonB.setOnClickListener {
            dogruKontrol(binding.buttonB)
            soruSayacKontrol()

        }
        binding.buttonC.setOnClickListener {
            dogruKontrol(binding.buttonC)
            soruSayacKontrol()

        }
        binding.buttonD.setOnClickListener {
            dogruKontrol(binding.buttonD)
            soruSayacKontrol()

        }
    }

    fun soruYukle(){
        binding.textViewSoru.text="${soruSayac+1}.Soru"

        dogruSoru=sorular.get(soruSayac)
        binding.imageView.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))
        yanlisSecenekler=Bayrakdao().rastgele3YanlisSecenekGetir(vt,dogruSoru.bayrak_id)
        tumSecenekler= HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))
        binding.buttona.text=tumSecenekler.elementAt(0).bayrak_ad
        binding.buttonB.text=tumSecenekler.elementAt(1).bayrak_ad
        binding.buttonC.text=tumSecenekler.elementAt(2).bayrak_ad
        binding.buttonD.text=tumSecenekler.elementAt(3).bayrak_ad
    }

    fun soruSayacKontrol(){
        soruSayac++
        if(soruSayac!=5){
            soruYukle()
        }else{
            val intent=Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)

            finish()
        }
    }
    fun dogruKontrol(button: Button){
        val buttonYazi=button.text.toString()
        val dogruCevap=dogruSoru.bayrak_ad
        if(buttonYazi==dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }
        binding.textViewDogru.text="Doğru:$dogruSayac"
        binding.textViewYanlis.text="Yanlış:$yanlisSayac"
    }
}