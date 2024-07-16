//
//  main.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski
//
import Foundation
let b1:Brain = Brain()
b1.addLogicalSkill(DiHelloWorld())
b1.addHardwareSkill(DiSysOut())
b1.doIt(ear: "hello", skin: "", eye: "")
