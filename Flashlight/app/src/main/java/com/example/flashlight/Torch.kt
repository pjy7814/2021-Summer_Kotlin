package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch (context: Context) {
    private var cameraId : String?=null
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    private fun getCameraId() : String?{
        val cameraIds = cameraManager.cameraIdList
        for(id in cameraIds){
            val info = cameraManager.getCameraCharacteristics(id)
            val flashAvailale = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)
            if(flashAvailale != null
                    && flashAvailale
                    && lensFacing != null
                    && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id
            }
        }
        return null
    }
    init{
        cameraId = getCameraId()
    }
    fun flashOn() {
        cameraManager.setTorchMode(cameraId!!, true)
    }
    fun flashOff() {
        cameraManager.setTorchMode(cameraId!!, false)
    }
}