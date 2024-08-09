package com.example.bluetooth_chat_app.data.chat

import android.bluetooth.BluetoothSocket
import com.example.bluetooth_chat_app.domain.chat.ConnectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class BluetoothDataTransferService(
    private val socket: BluetoothSocket
) {
    fun listenIncomingMessages(): Flow<ConnectionResult>{
        return flow {
            if(!socket.isConnected){
                return@flow
            }
            val buffer = ByteArray(1024)
            while (true){
                val byteCount = try {
                    socket.inputStream
                }catch (e: IOException)
            }
        }
    }
}