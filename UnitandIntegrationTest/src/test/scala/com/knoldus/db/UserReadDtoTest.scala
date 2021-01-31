package com.knoldus.db

import org.scalatest.flatspec.AnyFlatSpec

class UserReadDtoTest extends AnyFlatSpec {

  "User" should "exist" in {

    val userReadDto = new UserReadDto
    val result = userReadDto.getUserByName("Vaibhav")
    assert(result.isDefined)
  }

  "User" should "not exist" in {

    val userReadDto = new UserReadDto
    val result = userReadDto.getUserByName("Nitin")
    assert(result.isEmpty)
  }
}