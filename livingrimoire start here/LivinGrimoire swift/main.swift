//
//  main.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski
//
import Foundation

let b1:Brain = Brain()
b1.logicChobit.addSkill(skill: DiHelloWorld())
b1.hardwareChobit.addSkill(skill: DiSysOut())
b1.doIt(ear: "hello", skin: "", eye: "")
