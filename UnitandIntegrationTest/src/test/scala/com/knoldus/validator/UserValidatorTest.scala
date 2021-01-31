package com.knoldus.validator

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.User
import com.knoldus.models.Company
import org.scalatest.flatspec.AnyFlatSpec
import org.mockito.MockitoSugar.{mock, when}

class UserValidatorTest extends AnyFlatSpec {

  val googleCompany: Company = Company("Google", "google@gmail.com", "Noida")
  val vaibhavUser: User = User("Vaibhav","Bansal","knoldus","vaibhav@knoldus.com")
  val mockedCompanyREadDto: CompanyReadDto = mock[CompanyReadDto]
  val mockedEmailValidator: EmailValidator = mock[EmailValidator]

  "User" should "be a valid user" in{

    when(mockedCompanyREadDto.getCompanyByName(vaibhavUser.companyName)) thenReturn Option(googleCompany)
    when(mockedEmailValidator.emailIdIsValid(vaibhavUser.emailId)) thenReturn true
    val userValidator= new UserValidator(mockedCompanyREadDto ,  mockedEmailValidator)
    val result=userValidator.userIsValid(vaibhavUser)
    assert(result)
  }

  "User" should "be a invalid user as it already exists" in{

    when(mockedCompanyREadDto.getCompanyByName(vaibhavUser.companyName)) thenReturn None
    when(mockedEmailValidator.emailIdIsValid(vaibhavUser.emailId)) thenReturn true
    val userValidator= new UserValidator(mockedCompanyREadDto ,  mockedEmailValidator)
    val result=userValidator.userIsValid(vaibhavUser)
    assert(!result)
  }

  "User" should "be a invalid user and email id is valid" in{

    when(mockedCompanyREadDto.getCompanyByName(vaibhavUser.companyName)) thenReturn Option(googleCompany)
    when(mockedEmailValidator.emailIdIsValid(vaibhavUser.emailId)) thenReturn false
    val userValidator= new UserValidator(mockedCompanyREadDto ,  mockedEmailValidator)
    val result=userValidator.userIsValid(vaibhavUser)
    assert(!result)
  }

  "User" should "be a invalid user and as email id is invalid" in{

    when(mockedCompanyREadDto.getCompanyByName(vaibhavUser.companyName)) thenReturn None
    when(mockedEmailValidator.emailIdIsValid(vaibhavUser.emailId)) thenReturn false
    val userValidator= new UserValidator(mockedCompanyREadDto ,  mockedEmailValidator)
    val result=userValidator.userIsValid(vaibhavUser)
    assert(!result)
  }
}