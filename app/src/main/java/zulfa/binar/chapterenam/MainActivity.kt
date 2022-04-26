package zulfa.binar.chapterenam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import zulfa.binar.chapterenam.pertama.HitungUmur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contohThreadSatu()
        contohHandler()
        contohHandlerThread()
        contohHT()
    }

    fun contohThreadSatu(){

        Thread(Runnable {

            tv_satu.post(Runnable { tv_satu.text = "Hello" })
            tv_dua.postDelayed(Runnable { tv_dua.text = "Welcome" }, 2000)

            btnChange.setOnClickListener {
                tv_satu.post(Runnable { tv_satu.text = "Hello Binar Academy" })
            }

            btnHitungUmur.setOnClickListener {
                startActivity(Intent(this, HitungUmur::class.java))
            }
        }).start()
    }

    fun contohHandler(){

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            //startActivity(Intent(this, HitungUmur::class.java))
        }, 2000)

        Handler().post(Runnable {
            tv_dua.setText("HAHAHAHA")
        })
    }

    fun contohHandlerThread(){

        val han = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val pesan2 = msg.obj as String
                tv_dua.text = pesan2
            }
        }

        Thread(Runnable {
            val a = "Contoh Handler Thread"
            val pesan = Message.obtain()
            pesan.obj = a
            pesan.target = han
            pesan.sendToTarget()
        }).start()
    }

    fun contohHT(){
        val targetHandler = object  : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val a = msg.obj as String
                tv_satu.text = a
            }
        }

        Thread(Runnable {
            val a = Message.obtain()
            a.obj = "qwerty"
            a.target = targetHandler
            a.sendToTarget()
        }).start()
    }
}