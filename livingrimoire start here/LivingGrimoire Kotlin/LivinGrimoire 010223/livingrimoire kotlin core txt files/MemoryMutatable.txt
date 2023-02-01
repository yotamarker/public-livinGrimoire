package lgkt

class MemoryMutatable(kokoro: Kokoro, aPart: Mutatable) : Mutatable() {
    /*
     * an adaptor pattern to the Mutatable (algorithm part)
     * the object will load the last mutated state which is the last and optimized
     * mutation used.
     * upon mutation the new last mutation is saved so it can be loaded for the next time
     * mutations happen in the DExplorer class and triggered when the Mutatbles' failure method
     * returns enumFail.failure
     * you can code said enumFail.failure to return under chosen conditions in the action method of
     * the MemoryMutatable object. (sub class of this class)
     */
    var kokoro: Kokoro
    var aPart: Mutatable

    init {
        this.kokoro = kokoro
        // load the last saved mutatable
        this.aPart = kokoro.grimoireMemento.load(aPart!!)
    }

    fun actualAction(ear: String, skin: String, eye: String): String {
        return aPart.action(ear, skin, eye)
    }

    override fun action(ear: String, skin: String, eye: String): String {
        kokoro.`in`(this)
        val result = actualAction(ear, skin, eye)
        kokoro.out(completed(), failure(""))
        return result
    }

    override fun failure(input: String): enumFail {
        return aPart.failure(input)
    }

    override fun completed(): Boolean {
        return aPart.completed()
    }

    override fun clone(): Mutatable {
        return MemoryMutatable(kokoro, aPart.clone())
    }

    override fun mutation(): Mutatable? {
        // upon mutation the last mutation is saved
        val tempAP = aPart.mutation()
        kokoro.grimoireMemento.reqquipMutation(tempAP!!.javaClass.simpleName)
        return MemoryMutatable(kokoro, tempAP)
    }

    override fun myName(): String {
        return aPart.myName()
    }

    override fun getMutationLimit(): Int {
        return aPart.getMutationLimit()
    }
}