package com.valasapplication.app.modules.confirmyouremail.`data`.model

import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ConfirmYourEmailModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmyour: String? =
      MyApp.getInstance().resources.getString(R.string.msg_confirm_your_email)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWevesentfive: String? =
      MyApp.getInstance().resources.getString(R.string.msg_we_ve_sent_5_digits)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEnter: String? = MyApp.getInstance().resources.getString(R.string.msg_enter_verification)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNeedhelpfaq: String? = MyApp.getInstance().resources.getString(R.string.msg_need_help_faq)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etResendtimeOneValue: String? = null
)
