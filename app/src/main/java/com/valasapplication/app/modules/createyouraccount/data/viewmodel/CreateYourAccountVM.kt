package com.valasapplication.app.modules.createyouraccount.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valasapplication.app.modules.createyouraccount.`data`.model.CreateYourAccountModel
import org.koin.core.KoinComponent

class CreateYourAccountVM : ViewModel(), KoinComponent {
  val createYourAccountModel: MutableLiveData<CreateYourAccountModel> =
      MutableLiveData(CreateYourAccountModel())

  var navArguments: Bundle? = null
}
