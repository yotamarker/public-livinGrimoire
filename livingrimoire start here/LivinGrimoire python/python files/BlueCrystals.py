#  for langauge skills
from skills import DiBlueCrystal


class RussianWordGems:
    def __init__(self):
        self._o1: DiBlueCrystal = DiBlueCrystal()  # private variable with type annotation
        map: dict[str, str] = {
            "привет": "hello",
            "доброе утро": "good morning",
            "книга": "book"
        }  # dictionary with type annotation and new lines
        self._o1.addCategory(map)  # private method call

    def retSkill(self) -> DiBlueCrystal:  # method with return type annotation
        return self._o1
