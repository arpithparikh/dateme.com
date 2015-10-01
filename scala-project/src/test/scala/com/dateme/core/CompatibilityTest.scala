package com.dateme.core

import org.specs2.mutable.Specification
import com.dateme.core.model.User

class CompatibilitySpec extends Specification {
  "Two users with same favorite number" should {
    "have perfect score" in {
      val user1 = User("a@b.com", 5)
      val user2 = User("x@y.com", 5)
      val score = LoveEngine.score(user1, user2)
      10 === score
    }
  }
}

