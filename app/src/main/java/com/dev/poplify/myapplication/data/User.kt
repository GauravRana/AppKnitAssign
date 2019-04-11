package com.dev.poplify.myapplication.data

import android.databinding.ObservableInt
import com.dev.poplify.myapplication.data.UserDetail


/***
 * Data class
 */

data class User (var userDetails: MutableList<UserDetail> = mutableListOf(), val info: ObservableInt = ObservableInt()){

    fun addUserDetail(userDetail: UserDetail){
        userDetails.add(userDetail)
        info.set(info.get())
    }
}