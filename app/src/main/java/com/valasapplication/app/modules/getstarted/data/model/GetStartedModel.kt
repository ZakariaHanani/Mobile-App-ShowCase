package com.valasapplication.app.modules.getstarted.`data`.model

import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class GetStartedModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtGrowyour: String? = MyApp.getInstance().resources.getString(R.string.msg_grow_your_insight)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtExplorethe: String? =
      MyApp.getInstance().resources.getString(R.string.msg_explore_the_world)

)
