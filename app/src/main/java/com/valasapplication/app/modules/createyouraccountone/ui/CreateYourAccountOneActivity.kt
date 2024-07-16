package com.valasapplication.app.modules.createyouraccountone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.valasapplication.app.R
import com.valasapplication.app.RetrofitClient
import com.valasapplication.app.SignUpTask
import com.valasapplication.app.appcomponents.base.BaseActivity
import com.valasapplication.app.databinding.ActivityCreateYourAccountOneBinding
import com.valasapplication.app.modules.confirmyouremail.ui.ConfirmYourEmailActivity
import com.valasapplication.app.modules.createyouraccountone.data.viewmodel.CreateYourAccountOneVM
import java.util.regex.Pattern
import kotlin.String
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateYourAccountOneActivity :
  BaseActivity<ActivityCreateYourAccountOneBinding>(R.layout.activity_create_your_account_one), SignUpTask.SignUpListener {
  private val viewModel: CreateYourAccountOneVM by viewModels<CreateYourAccountOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.createYourAccountOneVM = viewModel
    setUpClicks()
  }

  override fun setUpClicks() {
    val createAccountButton: Button = findViewById(R.id.btnContinue)
    createAccountButton.setOnClickListener {
      val email = intent.getStringExtra(EXTRA_EMAIL) ?: ""
      validate(email)
      // val destIntent = ConfirmYourEmailActivity.getIntent(this, null)
      // destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      // startActivity(destIntent)
    }
  }

  private fun validate(email: String) {
    isEmailDuplicate(email) { isDuplicate ->
      if (isDuplicate) {
        // Show error message for duplicate email
        runOnUiThread {
          Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
        }
      } else {
        // Proceed with sign-up process
        runOnUiThread {
          signUp(email)
        }
      }
    }
  }

  private fun signUp(email: String) {
    // Get other user input (first name, last name, password) from the UI fields
    val firstName = binding.etFirstName.text.toString().trim()
    val lastName = binding.etLastName.text.toString().trim()
    val password = binding.etPassword.text.toString()
    val confirmPassword = binding.etConfirmpassword.text.toString()

    // Validate input fields
    if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
      return
    }

    // Validate email format
    if (!isEmailValid(email)) {
      Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
      return
    }

    // Validate password and confirmation
    if (password != confirmPassword) {
      Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
      return
    }

    // Validate password strength
    if (!isPasswordStrong(password)) {
      Toast.makeText(this, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character", Toast.LENGTH_LONG).show()
      return
    }

    // Call the SignUpTask to perform the sign-up operation
    val signUpTask = SignUpTask(this)
    signUpTask.execute(email, firstName, lastName, password)
  }

  // Function to validate email format
  private fun isEmailValid(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
  }

  private fun isEmailDuplicate(email: String, callback: (Boolean) -> Unit) {
    val apiService = RetrofitClient.apiService
    val call = apiService.checkEmailDuplicate(email)

    call.enqueue(object : Callback<Boolean> {
      override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
        if (response.isSuccessful) {
          val isDuplicate = response.body() ?: false
          callback(isDuplicate)
        } else {
          // Handle API error
          callback(false)
        }
      }

      override fun onFailure(call: Call<Boolean>, t: Throwable) {
        // Handle network error
        callback(false)
      }
    })
  }

  // Function to validate password strength
  private fun isPasswordStrong(password: String): Boolean {
    val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    return pattern.matcher(password).matches()
  }

  override fun onSignUpResult(result: String) {
    // Handle the sign-up result here (e.g., show a toast message)
    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

    // Proceed with navigation or other actions based on the result
    if (result == "Sign Up successful") {
      val destIntent = ConfirmYourEmailActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "CREATE_YOUR_ACCOUNT_ONE_ACTIVITY"
    private const val EXTRA_EMAIL = "extra_email"

    fun getIntent(context: Context, email: String?, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CreateYourAccountOneActivity::class.java)
      destIntent.putExtra(EXTRA_EMAIL, email)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
