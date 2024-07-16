package com.valasapplication.app.extensions

import android.util.Patterns
import android.widget.Toast
import com.valasapplication.app.R
import com.valasapplication.app.appcomponents.di.MyApp
import kotlin.Boolean
import kotlin.String

/**
 * Password should have, 
 * - at least a upper case letter
 * - at least a lower case letter
 * - at least a digit
 * - at least a special character [@#$%^&+=]
 * - length of at least 4
 * ⚫ no white space allowed
 */
public fun String?.isValidPassword(isMandatory: Boolean = false): Boolean {
  if (isNullOrEmpty()) {
    if (isMandatory) {
      Toast.makeText(
              MyApp.getInstance(),
              R.string.msg_please_enter_valid_pa,
              Toast.LENGTH_SHORT
          ).show()
    }
    return !isMandatory
  }
  val passwordRegex =
      "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%^&+=])(?=\\S+${'$'}).{4,}${'$'}".toRegex()
  val result = 
  passwordRegex.matches(this)
  if (!result) {
    Toast.makeText(
            MyApp.getInstance(),
            R.string.msg_please_enter_valid_pa,
            Toast.LENGTH_SHORT
        ).show()
  }
  return result
}

public fun String?.isValidEmail(isMandatory: Boolean = false): Boolean {
  if (isNullOrEmpty()) {
    if (isMandatory) {
      Toast.makeText(
              MyApp.getInstance(),
              R.string.msg_please_enter_valid_email,
              Toast.LENGTH_SHORT
          ).show()
    }
    return !isMandatory
  }
  val result = 
  Patterns.EMAIL_ADDRESS.matcher(this).matches()
  if (!result) {
    Toast.makeText(
            MyApp.getInstance(),
            R.string.msg_please_enter_valid_email,
            Toast.LENGTH_SHORT
        ).show()
  }
  return result
}

public fun String?.isNumeric(isMandatory: Boolean = false): Boolean {
  if (isNullOrEmpty()) {
    if (isMandatory) {
      Toast.makeText(
              MyApp.getInstance(),
              R.string.msg_please_enter_valid_nu,
              Toast.LENGTH_SHORT
          ).show()
    }
    return !isMandatory
  }
  val numberRegex = "^[0-9]*${'$'}".toRegex()
  val result = 
  numberRegex.matches(this)
  if (!result) {
    Toast.makeText(
            MyApp.getInstance(),
            R.string.msg_please_enter_valid_nu,
            Toast.LENGTH_SHORT
        ).show()
  }
  return result
}
