package com.valasapplication.app.modules.getstarted.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valasapplication.app.modules.getstarted.`data`.model.GetStartedModel
import org.koin.core.KoinComponent

class GetStartedVM : ViewModel(), KoinComponent {
  val getStartedModel: MutableLiveData<GetStartedModel> = MutableLiveData(GetStartedModel())

  var navArguments: Bundle? = null
}
