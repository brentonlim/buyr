package edu.towson.cosc435.buyr.options

import java.util.*

class User {
    var id: Int
        get() = id
        set(id:Int) {this.id = id}

    var email: String
        get() = email
        set(email:String) {this.email = email}

    var password : String
        get() = password
        set(password:String) {this.password = password}

    constructor(id: Int, email: String, password: String) {
        this.id = id
        this.email = email
        this.password = password
    }

}
