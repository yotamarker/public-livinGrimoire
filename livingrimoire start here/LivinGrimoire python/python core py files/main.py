from abc import ABC, abstractmethod
from LivinGrimoireCore import *

class Personality2(PersonalityLight):
    # Override
    def __init__(self, *absDictionaryDB: AbsDictionaryDB):
        super().__init__(absDictionaryDB)
        super().getdClassesLv1().append(DiHelloWorld(self.getKokoro()))


if __name__ == "__main__":
    test_personality: Personality2 = Personality2()
    chii: ChobitsLight = ChobitsLight(test_personality)
    result = chii.think("hello", "", "")
    print(result)