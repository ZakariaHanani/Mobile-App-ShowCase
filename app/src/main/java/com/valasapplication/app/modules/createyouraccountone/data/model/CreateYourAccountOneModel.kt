package com.valasapplication.app.modules.createyouraccountone.`data`.model

import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class CreateYourAccountOneModel(
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
  var txtFirstname: String? = MyApp.getInstance().resources.getString(R.string.lbl_first_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLastname: String? = MyApp.getInstance().resources.getString(R.string.lbl_last_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPassword: String? = MyApp.getInstance().resources.getString(R.string.lbl_password)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirm: String? = MyApp.getInstance().resources.getString(R.string.msg_confirm_password)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etFirstNameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLastNameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etConfirmpasswordValue: String? = null
)
