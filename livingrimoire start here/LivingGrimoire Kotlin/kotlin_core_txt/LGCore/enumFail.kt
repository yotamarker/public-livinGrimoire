package com.yotamarker.lgkotlinfull.LGCore

/* failure types
 * ok : no fail
 * requip : item should be added
 * cloudian : algorithm goes to stand by in its Dclass
 * fail : no input
 * */
enum class enumFail {
    fail, requip, dequip, cloudian, ok
}