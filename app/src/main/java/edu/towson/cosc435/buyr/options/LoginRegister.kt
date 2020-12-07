package edu.towson.cosc435.buyr.options

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import edu.towson.cosc435.buyr.MainActivity
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.options.SessionManager
import kotlinx.android.synthetic.main.user_login.*
import java.util.*


//https://www.youtube.com/watch?v=BXejFpaEwRc
//tutorialspoint article

class LoginRegister : AppCompatActivity() {
    //private lateinit var id: Int
    private lateinit var email : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        //email = user_email.editableText.toString()
        //password = user_password.editableText.toString()

        val login = findViewById<Button>(R.id.login_btn)
        login.setOnClickListener { login() }

        Log.d("hello", "hello")

    }

//    private lateinit var sessionManagement:SessionManager(this)
      private val sessionManagement = SessionManager(this)

    override fun onStart() {
        super.onStart()

        checkSession()
    }

    private fun checkSession() {
        //check if user is logged in then move to toMainApp
        val userID = sessionManagement.getSession()

        if(userID != -1){
            //ID validation --> move to toMainApp()
            toMainApp()
        }else{
            //todo: decide what to do if user is not logged in
        }
    }

    private fun login() {
        //login
        //todo: accept user input
        val user = User(1,"blim@mail.com","1234")

        sessionManagement.saveSession(user)

        //proceed to app's MainActivity here [LOGGED IN]:
        toMainApp()

    }

    private fun toMainApp() {
        //todo make sure the re-direct to next intent is to where list creation can start!!
        //from 'this' to whichever activity you want
        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

//    private fun createId(){
//        id = UUID.randomUUID()
//    }
}