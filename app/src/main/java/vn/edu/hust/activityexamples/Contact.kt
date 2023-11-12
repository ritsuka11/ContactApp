package vn.edu.hust.activityexamples

import java.io.Serializable

data class Contact(val id: Int, val name: String, val phoneNumber: String, val email: String) : Serializable

