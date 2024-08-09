package com.example.bluetooth_chat_app.data.chat

import android.bluetooth.BluetoothSocket
import com.example.bluetooth_chat_app.domain.chat.ConnectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BluetoothDataTransferService(
    private val socket: BluetoothSocket
) {
    fun listenIncomingMessages(): Flow<ConnectionResult>{
        return flow {
            if(!socket.isConnected){
                return@flow
            }
        }
    }
}