# This is a sample Python script.
from LivinGrimoireCoreV2 import *

class DeepCopier:
    def copyList(self,original: list[str]) -> list[str]:
        deepCopy: list[str] = []
        for item in original:
            deepCopy.append(item)
        return deepCopy
if __name__ == "__main__":
    l1:list[str] = []
    l1.append("shit")
    l2 = l1
    l1[0] = "jizz"
    print(l2[0])
    neo:Chobits = Chobits()
    neo.addSkill(DiHelloWorld())
    print(neo.think("","",""))
    print(neo.think("hello", "", ""))
    print(neo.think("", "", ""))

