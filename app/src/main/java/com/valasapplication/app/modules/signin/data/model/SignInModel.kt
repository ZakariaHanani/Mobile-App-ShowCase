package com.valasapplication.app.modules.signin.`data`.model

import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class SignInModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWelcomeback: String? = MyApp.getInstance().resources.getString(R.string.lbl_welcome_back)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLetsloginfor: String? =
      MyApp.getInstance().resources.getString(R.string.msg_let_s_login_for)
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
  var txtPassword: String? = MyApp.getInstance().resources.getString(R.string.lbl_password)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtForgotpassword: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_forgot_password)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtYoucanconnect: String? =
      MyApp.getInstance().resources.getString(R.string.msg_you_can_connect)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_don_t_have_an_account)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etPhoneNumberValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null
)
