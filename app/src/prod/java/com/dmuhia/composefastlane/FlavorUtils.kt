package com.dmuhia.composefastlane

class FlavorUtils {
    companion object{
        fun printFlavorName(){
            android.util.Log.e("TAG", "printFlavorName: ${BuildConfig.FLAVOR}")
        }
    }
}