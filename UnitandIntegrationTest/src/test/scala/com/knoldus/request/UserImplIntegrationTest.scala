package com.knoldus.request

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.User
import com.knoldus.validator.{EmailValidator, UserValidator}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplIntegrationTest extends AnyFlatSpec {

  val companyReadDto = new CompanyReadDto
  val emailValidator = new EmailValidator
  val userValidator = new UserValidator(companyReadDto, emailValidator)
  val userImpl = new UserImpl(userValidator)

  "User" should "be valid" in {

    val vaibhavUser: User = User("Vaibhav","Bansal","Knoldus","vaibhav.bansal@knoldus.com")
    val result =  userImpl.createUser(vaibhavUser)
    assert(result.isDefined)
  }

  "User" should "be invalid as it company doesnot exists in DB" in {

    val nitinUser: User = User("Nitin","Mittal","Google","karan@knoldus.com")
    val result =  userImpl.createUser(nitinUser)
    assert(result.isEmpty)
  }

  "Employee" should "be invalid as email id is invalid" in {

    val vaibhavEmployee: User = User("Vaibhav","Bansal","Knoldus","vaibhav.bansal@knoldus.com")
    val result = userImpl.createUser(vaibhavEmployee)
    assert(result.isEmpty)
  }

  "Employee" should "be invalid as email id is invalid and company does not exists in DB" in {

    val vaibhavUser: User = User("Vaibhav","Bansal","Google","vaibhav.knoldus.com")
    val result =  userImpl.createUser(vaibhavUser)
    assert(result.isEmpty)
  }
}
