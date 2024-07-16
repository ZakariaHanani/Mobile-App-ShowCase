package com.valasapplication.app.modules.createyouraccount.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.base.BaseActivity
import com.valasapplication.app.databinding.ActivityCreateYourAccountBinding
import com.valasapplication.app.modules.createyouraccount.data.viewmodel.CreateYourAccountVM
import com.valasapplication.app.modules.createyouraccountone.ui.CreateYourAccountOneActivity

class CreateYourAccountActivity :
  BaseActivity<ActivityCreateYourAccountBinding>(R.layout.activity_create_your_account) {

  private val viewModel: CreateYourAccountVM by viewModels()

  override fun onInitialized() {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.createYourAccountVM = viewModel
    setUpClicks()
  }

  override fun setUpClicks() {
    val createAccountButton: Button = findViewById(R.id.btnContinue)
    createAccountButton.setOnClickListener {
      val email = binding.etPhoneNumber.text.toString().trim()
      val destIntent = CreateYourAccountOneActivity.getIntent(this, email, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "CreateYourAccountActivity"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CreateYourAccountActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
