package com.dateme.core

import com.dateme.core.model.User

object LoveEngine {

  def score(user1: User, user2: User): Int = 10 - Math.abs(user1.number - user2.number)

}
