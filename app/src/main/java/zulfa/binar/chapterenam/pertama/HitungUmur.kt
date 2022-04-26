package zulfa.binar.chapterenam.pertama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hitung_umur.*
import kotlinx.android.synthetic.main.activity_main.*
import zulfa.binar.chapterenam.R

class HitungUmur : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitung_umur)

        contohThreadSatu()
    }

    fun contohThreadSatu(){

        Thread(Runnable {

            btnHitung.setOnClickListener {

                val nama = et_nama.text.toString()
                val umur = et_umur.text.toString().toInt()
                val usia = 2022 - umur

                tv_nama.post(Runnable {
                    tv_nama.text = nama
                })

                tv_umur.post(Runnable {
                    tv_umur.text = usia.toString()
                })
            }
        }).start()
    }
}