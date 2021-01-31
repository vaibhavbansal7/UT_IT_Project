package com.knoldus.validator

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.Company
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class CompanyValidatorTest extends AnyFlatSpec {

  val knoldusCompany: Company = Company("Knoldus", "knoldus@gmail.com", "Noida")
  val mockedCompanyReadDto: CompanyReadDto = mock[CompanyReadDto]
  val mockedEmailValidator: EmailValidator = mock[EmailValidator]

  "Company" should "be valid" in {

    when(mockedCompanyReadDto.getCompanyByName(knoldusCompany.name)) thenReturn Option(knoldusCompany)
    when(mockedEmailValidator.emailIdIsValid(knoldusCompany.emailId)) thenReturn true
    val companyValidator = new CompanyValidator(mockedCompanyReadDto,mockedEmailValidator)
    val result = companyValidator.companyIsValid(knoldusCompany)
    assert(result)
  }

  "Company" should "be invalid as it already exists in the DB" in {

    when(mockedCompanyReadDto.getCompanyByName(knoldusCompany.name)) thenReturn None
    when(mockedEmailValidator.emailIdIsValid(knoldusCompany.emailId)) thenReturn true
    val companyValidator = new CompanyValidator(mockedCompanyReadDto,mockedEmailValidator)
    val result = companyValidator.companyIsValid(knoldusCompany)
    assert(!result)
  }

  "Company" should "be valid as the email id is invalid" in {

    when(mockedCompanyReadDto.getCompanyByName(knoldusCompany.name)) thenReturn Option(knoldusCompany)
    when(mockedEmailValidator.emailIdIsValid(knoldusCompany.emailId)) thenReturn false
    val companyValidator = new CompanyValidator(mockedCompanyReadDto,mockedEmailValidator)
    val result = companyValidator.companyIsValid(knoldusCompany)
    assert(!result)
  }

  "Company" should "be invalid as the email id is not valid and it already exists in DB " in {

    when(mockedCompanyReadDto.getCompanyByName(knoldusCompany.name)) thenReturn None
    when(mockedEmailValidator.emailIdIsValid(knoldusCompany.emailId)) thenReturn false
    val companyValidator = new CompanyValidator(mockedCompanyReadDto,mockedEmailValidator)
    val result = companyValidator.companyIsValid(knoldusCompany)
    assert(!result)
  }
}