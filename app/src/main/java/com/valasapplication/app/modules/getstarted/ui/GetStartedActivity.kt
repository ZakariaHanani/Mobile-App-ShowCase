package com.valasapplication.app.modules.getstarted.ui

import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.base.BaseActivity
import com.valasapplication.app.databinding.ActivityGetStartedBinding
import com.valasapplication.app.modules.getstarted.`data`.viewmodel.GetStartedVM
import com.valasapplication.app.modules.signin.ui.SignInActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class GetStartedActivity : BaseActivity<ActivityGetStartedBinding>(R.layout.activity_get_started) {
  private val viewModel: GetStartedVM by viewModels<GetStartedVM>()

  private val REQUEST_CODE_SIGN_IN_ACTIVITY: Int = 931

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.getStartedVM = viewModel
    Handler(Looper.getMainLooper()).postDelayed( {
      val destIntent = SignInActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
      }, 3000)
    }

    override fun setUpClicks(): Unit {
      binding.btnGetStarted.setOnClickListener {
        val destIntent = SignInActivity.getIntent(this, null)
        startActivityForResult(destIntent, REQUEST_CODE_SIGN_IN_ACTIVITY)
      }
    }

    companion object {
      const val TAG: String = "GET_STARTED_ACTIVITY"

    }
  }
