package pl.adamnowicki.nfcfinder

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by adamnowicki on 2020-02-14.
 */
class NfcFinderLifecycleObserver(private val callback: NfcLifecycleCallback) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        callback.enable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        callback.disable()
    }
}