package com.knoldus.request

import com.knoldus.models.{Company, User}
import org.scalatest.flatspec.AnyFlatSpec
import com.knoldus.validator.UserValidator
import org.mockito.MockitoSugar.{mock, when}

class UserImplTest extends AnyFlatSpec {

  val mockedUserValidate: UserValidator = mock[UserValidator]
  val nitinUser: User = User("Nitin","Mittal","knoldus","nitin.mittal@knoldus.com")
  val vaibhavUser: User = User("Vaibhav","Bansal","knoldus","vaibhav.bansal@knoldus.com")
  val knoldusCompany: Company = Company("Knoldus", "knoldus@gmail.com", "Noida")

  "User" should "be valid" in {

    val userImpl = new UserImpl(mockedUserValidate)
    when(mockedUserValidate.userIsValid(vaibhavUser)) thenReturn true
    val result = userImpl.createUser(vaibhavUser)
    assert(result.isDefined)
  }

  "User" should "be not valid" in {

    val userImpl = new UserImpl(mockedUserValidate)
    when(mockedUserValidate.userIsValid(nitinUser)) thenReturn false
    val result = userImpl.createUser(nitinUser)
    assert(result.isEmpty)
  }
}
