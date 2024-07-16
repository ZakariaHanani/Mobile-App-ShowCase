package com.valasapplication.app.modules.signin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.valasapplication.app.Activites.HomeActivity
import com.valasapplication.app.Navigation.NavigationActivityFragment
import com.valasapplication.app.R
import com.valasapplication.app.SignInTask
import com.valasapplication.app.appcomponents.base.BaseActivity
import com.valasapplication.app.appcomponents.googleauth.GoogleHelper
import com.valasapplication.app.databinding.ActivitySignInBinding
import com.valasapplication.app.modules.confirmyouremail.ui.ConfirmYourEmailActivity
import com.valasapplication.app.modules.createyouraccount.ui.CreateYourAccountActivity
import com.valasapplication.app.modules.signin.`data`.viewmodel.SignInVM
import org.json.JSONObject
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in),
  SignInTask.SignInListener {

  private val viewModel: SignInVM by viewModels<SignInVM>()

  private var signInSuccess = false

  private val REQUEST_CODE_CREATE_YOUR_ACCOUNT_ACTIVITY: Int = 980

  private lateinit var googleLogin: GoogleHelper

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySignInBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.signInVM = viewModel

    setUpClicks()
    googleLogin = GoogleHelper(this,
      { accountInfo ->
      }, { error ->
      })
  }

  override fun setUpClicks(): Unit {
    binding.btnSignIn.setOnClickListener {
      val email = binding.etPhoneNumber.text.toString()
      val password = binding.etPassword.text.toString()

      if (email.isEmpty() || password.isEmpty()) {
        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
      } else {
        signIn(email, password)
      }
    }
    binding.imageGoogleOne.setOnClickListener {
      googleLogin.login()
    }
    binding.txtConfirmation.setOnClickListener {
      val destIntent = CreateYourAccountActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CREATE_YOUR_ACCOUNT_ACTIVITY)
    }
  }

  private fun signIn(email: String, password: String) {

    binding.progressBar.visibility = View.VISIBLE

    SignInTask(this).execute(email, password)
  }

  override fun onSignInResult(result: String) {
    Log.d(TAG, "Sign-in result: $result")

    binding.progressBar.visibility = View.GONE

    try {
      val jsonObject = JSONObject(result)
      val status = jsonObject.getString("status")
      val message = jsonObject.getString("message")

      Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

      if (status == "success") {
        val user = jsonObject.getJSONObject("user")
        val firstName = user.getString("first_name")
        val lastName = user.getString("last_name")
        val email = user.getString("email")

        // Save user information to SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("first_name", firstName)
        editor.putString("last_name", lastName)
        editor.putString("email", email)
        editor.apply()
        signInSuccess = true
        NavigationActivityFragment.navigateTo(HomeActivity::class.java, this)
      }
    } catch (e: Exception) {
      e.printStackTrace()
      Toast.makeText(this, "Error: " + e.message, Toast.LENGTH_SHORT).show()
    }
  }

//  override fun onBackPressed() {
//
//    if (signInSuccess) {
//      val destIntent = HomeActivity.getIntent(this, null)
//      if (destIntent != null) {
//        destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//      }
//      startActivity(destIntent)
//      finish()
//    } else {
//
//      super.onBackPressed()
//    }
//  }

  companion object {
    const val TAG: String = "SIGN_IN_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SignInActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
