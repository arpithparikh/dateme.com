import unittest

from core.model import User
from core.love_engine import compatibility

class TestLoveEngine(unittest.TestCase):

    def perfect_compatibility(self):
        user1 = User("a@b.com", 4)
        user2 = User("x@y.com", 4)
        score = compatibility(user1, user2)
        self.assertEqual(10, score)


if __name__ == '__main__':
    unittest.main()



