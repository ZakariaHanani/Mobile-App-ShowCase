package com.valasapplication.app.modules.createyouraccount.`data`.model

import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class CreateYourAccountModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtCreateyour: String? =
      MyApp.getInstance().resources.getString(R.string.msg_create_your_account)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCreateaccount: String? =
      MyApp.getInstance().resources.getString(R.string.msg_create_account_for)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEmailorphone: String? =
      MyApp.getInstance().resources.getString(R.string.msg_email_or_phone_number)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etPhoneNumberValue: String? = null
)
