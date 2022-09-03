//
//  skills.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 31/08/2022.
//

import Foundation
class DiGreeter:DiSkillV2 {
    let pl:PlayGround = PlayGround()
    override func input(ear: String, skin: String, eye: String) {
        if ear == "hello" {
            outAlg = diSkillUtills.simpleVerbatimAlgorithm(algRepresantation: "greet", algMarker: "greeter", sayThis: "good \(pl.partOfDay())")
        }
    }
}
class DiParrot:DiSkillV2{
    let trgParrot:TrgParrot
    var out1 = ""
    let cldBool:CldBool = CldBool()
    override init(kokoro: Kokoro) {
        trgParrot = TrgParrot(kokoro: kokoro)
        super.init(kokoro: kokoro)
    }
    override func input(ear: String, skin: String, eye: String) {
        if cldBool.getModeActive() {return}
        trgParrot.input(ear: ear, skin: "", eye: "")
        out1 = trgParrot.getOutput()
        if !out1.isEmpty {
            self.outAlg = self.diSkillUtills.simpleCloudianVerbatimAlgorithm(algRepresantation: "parrot", cldBool: cldBool, algMarker: "r_parrot", sayThis: out1)
            out1 = ""
        }
    }
}
