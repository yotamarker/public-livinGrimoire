from __future__ import annotations  # for the builder pattern
from AXPython import TimeGate
from LivinGrimoire23 import Skill, Chobits


class AH1(Skill):
    def __init__(self, chobit: Chobits):
        super().__init__()  # Call the parent class constructor
        self._chobit: Chobits = chobit
        self._isActive: bool = False
        self._skills: list[Skill] = []
        self._tg: TimeGate = TimeGate(5)

    def input(self, ear, skin, eye):
        # hormone trigger
        if ear == "inject":
            self._tg.openForPauseMinutes()
            # equipp skills if not already are
            if not self._isActive:
                self._chobit.addSkills(*self._skills)
            self._isActive = True
            self.setSimpleAlg("hormone engaged")
            return
        # hormone wear off event:
        if self._isActive and self._tg.isClosed():
            self._isActive = False
            for skill in self._skills:
                self._chobit.removeSkill(skill)
            self.setSimpleAlg("hormone disengaged")

    def add_skill(self, skill: Skill) -> AH1:
        self._skills.append(skill)
        return self

    def add_skills(self, *skills: Skill) -> AH1:
        for skill in skills:
            self._skills.append(skill)
        return self

    def set_effect_duration(self, duration: int):
        self._tg.setPause(duration)
