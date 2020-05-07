package pl.adamnowicki.nfcfinder

import android.app.Activity
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings

/**
 * Created by adamnowicki on 2020-02-14.
 */
class NfcFinder (
    private val activity: Activity,
    private var nfcFoundCallback: () -> (Unit)
) : NfcLifecycleCallback {

    override fun enable() {

        val nfcAdapter = NfcAdapter.getDefaultAdapter(activity)
        nfcAdapter?.enableReaderMode(activity, { _ ->
            this.nfcFoundCallback.invoke()

        }, NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS, null)
    }

    override fun disable() {

        val nfcAdapter = NfcAdapter.getDefaultAdapter(activity)
        nfcAdapter?.disableReaderMode(activity)
    }

    fun openNFCSettings() {
        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
        activity.startActivity(intent)
    }
}