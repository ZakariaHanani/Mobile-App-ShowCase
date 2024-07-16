package com.valasapplication.app.modules.confirmyouremail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.base.BaseActivity
import com.valasapplication.app.databinding.ActivityConfirmYourEmailBinding
import com.valasapplication.app.modules.confirmyouremail.data.viewmodel.ConfirmYourEmailVM
import com.valasapplication.app.modules.signin.ui.SignInActivity
import kotlin.String

class ConfirmYourEmailActivity :
  BaseActivity<ActivityConfirmYourEmailBinding>(R.layout.activity_confirm_your_email) {
  private val viewModel: ConfirmYourEmailVM by viewModels<ConfirmYourEmailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.confirmYourEmailVM = viewModel
  }

  override fun setUpClicks(): Unit {
    val continueButton = findViewById<Button>(R.id.btnVerifyAnd)
    continueButton.setOnClickListener {
      // Navigate to the next activity when continueButton is clicked
      val destIntent = SignInActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "CONFIRM_YOUR_EMAIL_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ConfirmYourEmailActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
