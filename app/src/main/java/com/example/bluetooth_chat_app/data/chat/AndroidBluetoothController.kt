package com.example.bluetooth_chat_app.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import com.example.bluetooth_chat_app.Manifest
import com.example.bluetooth_chat_app.domain.chat.BluetoothController
import com.example.bluetooth_chat_app.domain.chat.BluetoothDevice
import com.example.bluetooth_chat_app.domain.chat.BluetoothDeviceDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import com.example.bluetooth_chat_app.data.chat.toBluetoothDeviceDomain


@SuppressLint("MissingPermission")
class AndroidBluetoothController(
    private val context: Context
): BluetoothController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy{
        bluetoothManager?.adapter
    }

    private val _scannedDevices= MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val scannedDevices: StateFlow<List<BluetoothDevice>>
        get() = _scannedDevices.asStateFlow()
    private val _pairedDevices= MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val pairedDevices: StateFlow<List<BluetoothDevice>>
        get() = _pairedDevices.asStateFlow()

    init{
        updatePairedDevices()
    }
    override fun startDiscovery() {
        TODO("Not yet implemented")
    }

    override fun stopDiscovery() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    private fun updatePairedDevices(){
        if(!hasPermission(android.Manifest.permission.BLUETOOTH_CONNECT)){
            return
        }
        bluetoothAdapter
            ?.bondedDevices
            ?.map { it.toBluetoothDeviceDomain() }
            ?.also {devices->
                _pairedDevices.update { devices } }
    }

    private fun hasPermission(permission: String): Boolean{
        return context.checkSelfPermission(permission)==PackageManager.PERMISSION_GRANTED
    }
}