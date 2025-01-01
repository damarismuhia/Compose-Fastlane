package com.dmuhia.composefastlane.dev

import com.dmuhia.composefastlane.BuildConfig

class FlavorUtils {
    companion object{
        fun printFlavorName(){
            android.util.Log.e("TAG", "printFlavorName: ${BuildConfig.FLAVOR} \n" +
                    "Feature is: ")
        }
    }

}