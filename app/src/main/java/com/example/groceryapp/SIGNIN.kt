package com.example.groceryapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*


class SIGNIN : AppCompatActivity() {
    companion object{
        val Req_Code: Int = 123;
    }
lateinit var  database:FirebaseDatabase
lateinit var dref:DatabaseReference

   val getaction=registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if(Req_Code==123) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            val exception=task.exception
            if(task.isSuccessful)
            {
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseauthwithgoogle(account.idToken)
                }
                catch(e:ApiException){
                    Toast.makeText(this, "Sorry Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Log.d("excep",exception.toString())
            }
        }
        else
        {
            Toast.makeText(this, "noooo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun firebaseauthwithgoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {

saveuserdata()

                val intent = Intent(this, Showlist::class.java)
                startActivity(intent)
                finish()


            }
        }


    }

    lateinit var GoogleSignInClient: GoogleSignInClient
    lateinit var  auth:FirebaseAuth;
    lateinit var  callbackManager: CallbackManager
    var user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
      auth=FirebaseAuth.getInstance()
        user= auth.currentUser
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignInClient=GoogleSignIn.getClient(this,gso);


    }
    fun googlesign(view:View)
    {


        val signInIntent: Intent = GoogleSignInClient.signInIntent
        getaction.launch(signInIntent);


    }
    fun saveuserdata()
    {

database=FirebaseDatabase.getInstance()
        dref=database.getReference("Users")
val name=auth.currentUser?.displayName
        val email=auth.currentUser?.email
        val id=auth.currentUser?.uid
        val uses=user(name, email,id)
        val uid=dref.push().key!!
        dref.child(uid).updateChildren(uses.toMap()).addOnSuccessListener {
            Toast.makeText(this, "Suceesfully registered", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Sorry try again", Toast.LENGTH_SHORT).show()
        }

    }
}



