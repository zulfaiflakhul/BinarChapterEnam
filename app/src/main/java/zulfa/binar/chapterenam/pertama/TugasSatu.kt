package zulfa.binar.chapterenam.pertama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_hitung_umur.*
import kotlinx.android.synthetic.main.activity_hitung_umur.btnHitung
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tugas_satu.*
import zulfa.binar.chapterenam.R

class TugasSatu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas_satu)

        hitungThread()
        contohHandlerThread()
    }

    fun hitungThread() {

        Thread(Runnable {

            btnHitungBMI.setOnClickListener {

                val bb = et_bb.text.toString().toInt()
                val tb = et_tb.text.toString().toFloat()
                val hasil = bb / (tb * tb)
                val ket : String

                if (hasil < 18.5){
                    ket = "Kurus"
                } else if (hasil >= 18.5 && hasil < 24.9){
                    ket = "Normal"
                } else if (hasil >= 25 && hasil < 29.9){
                    ket = "Overweight"
                } else if (hasil >= 30){
                    ket = "Obesitas"
                } else {
                    ket = ""
                }

                tv_hasil.post(Runnable {
                    tv_hasil.text = ket
                })
            }
        }).start()
    }

    fun contohHandlerThread(){

        btnHitungBMI.setOnClickListener {

            val han = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    val pesan2 = msg.obj as String
                    tv_hasil2.text = pesan2
                }
            }

            Thread(Runnable {

                val bb = et_bb.text.toString().toInt()
                val tb = et_tb.text.toString().toFloat()
                val hasil = bb / (tb * tb)
                val ket: String

                if (hasil < 18.5) {
                    ket = "Kurus"
                } else if (hasil >= 18.5 && hasil < 24.9) {
                    ket = "Normal"
                } else if (hasil >= 25 && hasil < 29.9) {
                    ket = "Overweight"
                } else if (hasil >= 30) {
                    ket = "Obesitas"
                } else {
                    ket = ""
                }

                val a = ket
                val pesan = Message.obtain()
                pesan.obj = a
                pesan.target = han
                pesan.sendToTarget()

            }).start()
        }

    }
}