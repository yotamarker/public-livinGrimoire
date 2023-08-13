package lgkt

import java.util.*


class GrimoireMemento(private val absDictionaryDB: AbsDictionaryDB) {
    private val rootToAPNumDic = Hashtable<String, String>()
    private val APNumToObjDic = Hashtable<String?, Mutatable>()
    fun load(obj: Mutatable): Mutatable {
        /*
         * load final mutation from memory of obj
         */
        val objName = obj.javaClass.simpleName
        val objRoot = objName.replace("\\d+".toRegex(), "")
        // if not in active DB try adding from external DB
        if (!rootToAPNumDic.containsKey(objRoot)) {
            val temp = absDictionaryDB.load(objRoot)
            if (temp !== "null") {
                rootToAPNumDic[objRoot] = temp
            }
        }
        if (!rootToAPNumDic.containsKey(objRoot)) {
            rootToAPNumDic[objRoot] = objName
            return obj
        }
        return if (rootToAPNumDic[objRoot] == objName) {
            obj
        } else {
            val APNum = rootToAPNumDic[objRoot]
            if (APNumToObjDic.containsKey(APNum)) {
                APNumToObjDic[APNum]!!.clone()
            } else {
                loadMutations(obj, objName, objRoot)
                APNumToObjDic[APNum]!!.clone()
            }
        }
    }

    fun reqquipMutation(mutationAPName: String) {
        // save mutation
        rootToAPNumDic[mutationAPName.replace("\\d+".toRegex(), "")] = mutationAPName
        absDictionaryDB.save(mutationAPName.replace("\\d+".toRegex(), ""), mutationAPName)
    }

    private fun loadMutations(obj: Mutatable, objName: String, objRoot: String) {
        // make sure all the AP mutation sets of obj are present
        // this assumes the last mutation mutates into the prime mutation
        var obj: Mutatable? = obj
        var mutant: Mutatable?
        do {
            APNumToObjDic[obj!!.javaClass.simpleName] = obj.clone()
            mutant = obj
            obj = mutant!!.mutation()
        } while (objName != obj!!.javaClass.simpleName)
    }

    fun simpleLoad(key: String?): String {
        return absDictionaryDB.load(key)
    }

    fun simpleSave(key: String, value: String) {
        if (key.startsWith("AP") || key.isEmpty() || value.isEmpty()) {
            return
        }
        absDictionaryDB.save(key, value)
    }
}