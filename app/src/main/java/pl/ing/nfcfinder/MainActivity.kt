package pl.ing.nfcfinder

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.adamnowicki.nfcfinder.NfcFinder
import pl.adamnowicki.nfcfinder.NfcFinderLifecycleObserver


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nfcFinder = NfcFinder(this) { vibrate() }
        lifecycle.addObserver(
            NfcFinderLifecycleObserver(
                nfcFinder
            )
        )

        buttonSettings.setOnClickListener {
            nfcFinder.openNFCSettings()
        }
        buttonFind.setOnClickListener {
            nfcFinder.enable()
        }
        buttonCancel.setOnClickListener {
            nfcFinder.disable()
        }
        buttonSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    @Suppress("DEPRECATION")
    private fun vibrate() {
        Log.d("MainActivity", "NFC found")
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v.vibrate(200)
        }
    }

}
