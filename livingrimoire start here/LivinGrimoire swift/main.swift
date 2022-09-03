//
//  main.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 21/06/2022.
//
import Foundation
let chii:Chobits = Chobits(personality: Personality()) // initialize waifu
chii.addSkill(skill: DiGreeter(kokoro: chii.kokoro)) // add a skill (only 1 line of code used per skill)
chii.addSkill(skill: DiParrot(kokoro: chii.kokoro))
print(chii.think(ear: "hello", skin: "", eye: ""))
print(chii.think(ear: "say fudge", skin: "", eye: ""))
print(chii.think(ear: "test", skin: "", eye: ""))
print(chii.think(ear: "test", skin: "", eye: ""))
print("start of test")
print(chii.think(ear: "hey", skin: "", eye: ""))
print(chii.think(ear: "", skin: "", eye: ""))
print(chii.think(ear: "honey", skin: "", eye: ""))
print(chii.think(ear: "shut up", skin: "", eye: ""))
print("expect nothing")
print(chii.think(ear: "test2", skin: "", eye: ""))
print("end")
let tg:TimeGate = TimeGate(pause: 1.0)
print("")
print(tg.isClosed())
