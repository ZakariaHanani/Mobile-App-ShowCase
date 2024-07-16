package com.valasapplication.app.appcomponents.googleauth

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleHelper(
    private val activity: ComponentActivity,
    private val onSuccess: (account: GoogleSignInAccount) -> Unit,
    private val onError: (statusCode: Int) -> Unit
) {

    private var mGoogleSignInClient: GoogleSignInClient? = null

    private val startForGoogleResult =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle Result
                handleResult(result.data)
            }
        }

    /** This method will be used for login
     *  How to use: on the button click call this method
     *  [Class_Object].login()
     * */
    fun login() {
        configureGoogleSignIn()
        val account = GoogleSignIn.getLastSignedInAccount(activity)
        if (account != null) {
            onSuccess(account)
        } else {
            signIn()
        }
    }

    /** configure Google Sign-In to request the user data required by application
     *  ID and basic profile are included in DEFAULT_SIGN_IN.
     *  To request users' email addresses as well, create the GoogleSignInOptions object with the requestEmail option.
     *  How to use:
     *  [Class_Object].configureGoogleSignIn()
     * */
    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    /** This function will be used handle the result
     *  @param data it is the intent passed as a parameter
     *  How to use:
     *  used from the onActivityResult
     *  if(requestCode == RC_SIGN_IN) {
     *         [Class_Object].handleResult(data)
     *  }
     * */
    private fun handleResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
            if (account != null) {
                onSuccess(account)
            }
        } catch (e: ApiException) {
            onError(e.statusCode)
        }
    }


    /** Call the signIn intent
     *  this function will call startActivityForResult from their result will be handled
     * */
    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        startForGoogleResult.launch(signInIntent)
    }


    /** Sign out the user
     *  How to use:
     *  [Class_Object].signOut { task ->  }
     * */
    fun signOut(onComplete: (task: Task<Void>) -> Unit) {
        mGoogleSignInClient?.signOut()?.addOnCompleteListener(
            activity
        ) { task -> onComplete(task) }
    }

    /** revoke the access, disconnect user's Google account from the app
     *  How to use:
     *  [Class_Object].revokeAccess{task -> }
     * */
    fun revokeAccess(onComplete: (task: Task<Void>) -> Unit) {
        mGoogleSignInClient!!.revokeAccess()
            .addOnCompleteListener(
                activity
            ) { task -> onComplete(task) }
    }
}