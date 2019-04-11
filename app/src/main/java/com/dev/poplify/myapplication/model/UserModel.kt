package com.dev.poplify.myapplication.model

import com.dev.poplify.myapplication.data.User
import com.dev.poplify.myapplication.data.UserDetail
import java.util.*

class UserModel(var user : User = User()) : Observable() {

    fun addDetail(name: String, info: String){
        val detail = UserDetail(name, info)
        user.addUserDetail(detail)
        setChanged()
        notifyObservers()
    }

}