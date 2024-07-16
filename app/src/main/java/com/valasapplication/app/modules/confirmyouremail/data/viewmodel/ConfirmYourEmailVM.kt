package com.valasapplication.app.modules.confirmyouremail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valasapplication.app.modules.confirmyouremail.`data`.model.ConfirmYourEmailModel
import org.koin.core.KoinComponent

class ConfirmYourEmailVM : ViewModel(), KoinComponent {
  val confirmYourEmailModel: MutableLiveData<ConfirmYourEmailModel> =
      MutableLiveData(ConfirmYourEmailModel())

  var navArguments: Bundle? = null
}
