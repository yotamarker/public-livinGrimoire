package com.yotamarker.lgkotlinfull.LGCore

class Permission private constructor(private var password: String, var lv1Name: String, private var lv2Name: String) {
    private var permissionLevel = 0
    fun getPermissionLevel(): Int {
        val result = permissionLevel
        permissionLevel = 0
        return result
    }

    fun setPermissionLevel(input: String) {
        if (input.contains(lv2Name)) {
            permissionLevel = 2
        } else if (input.contains(lv1Name)) {
            permissionLevel = 1
        }
        if (input.contains(lv2Name + " reset")) {
            permissionLevel = 0
        }
    }

    fun setPassword(oldPassword: String?, newPassword: String) {
        if (password == password) {
            password = newPassword
        }
    }

    fun setLv2Name(password: String, newName2: String): Boolean {
        if (this.password == password) {
            lv2Name = newName2
            return true
        }
        return false
    }

    fun getLv2Command(command: String): String {
        // returns cmd without lv2 name
        var result = command
        result = result.replace(lv2Name, "")
        return result.trim { it <= ' ' }
    }

    fun setLv1Name(password: String, newName: String): Boolean {
        if (this.password == password) {
            lv1Name = newName
            return true
        }
        return false
    }

    companion object {
        /*
     * uses two names as lv1,2 permission levels
     * requires password to change password or said names
     */
        private var singleton: Permission? = null
        fun newInstance(password: String, lv1Name: String, lv2Name: String): Permission? {
            if (singleton == null) {
                singleton = Permission(password, lv1Name, lv2Name)
            }
            return singleton
        }

        fun clsmsg(): String {
            return """
                   chaos ritual
                   
                   create two names
                   one soul will be called to the light
                   and one will be led by the darkness
                   and those souls of light and darkness will combine to create
                   the light of chaos
                   ...
                   A.G.I descended!
                   """.trimIndent()
        }
    }
}