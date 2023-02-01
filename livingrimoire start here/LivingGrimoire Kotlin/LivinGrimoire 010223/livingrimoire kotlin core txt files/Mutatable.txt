package lgkt


abstract class Mutatable {
    // one part of an algorithm, it is a basic simple action or sub goal
    abstract fun action(ear: String, skin: String, eye: String): String
    abstract fun completed(): Boolean
    abstract fun clone(): Mutatable
    open fun failure(input: String): enumFail {
        // Failure type only mutatable may use enumFail.fail
        return enumFail.ok
    }

    open fun getMutationLimit(): Int {
        /*
         * override this to the number of mutations a mutation series can perform, so at
         * least to 1 if you want mutations enabled.
         */
        return 0
    }

    open fun myName(): String {
        // Returns the class name
        return this.javaClass.simpleName
    }

    open fun mutation(): Mutatable? {
        return null
    }
}