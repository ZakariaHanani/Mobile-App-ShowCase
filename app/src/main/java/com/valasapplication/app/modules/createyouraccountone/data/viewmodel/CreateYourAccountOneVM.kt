package com.valasapplication.app.modules.createyouraccountone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valasapplication.app.modules.createyouraccountone.`data`.model.CreateYourAccountOneModel
import org.koin.core.KoinComponent

class CreateYourAccountOneVM : ViewModel(), KoinComponent {
  val createYourAccountOneModel: MutableLiveData<CreateYourAccountOneModel> =
      MutableLiveData(CreateYourAccountOneModel())

  var navArguments: Bundle? = null
}
