package com.yotamarker.lgkotlinfull.LGCore

abstract class AbsAlgPart {
    // one part of an algorithm, it is a basic simple action or sub goal
    abstract fun action(ear: String, skin: String, eye: String): String // return action string
    abstract fun itemize(): Boolean? // equip with item ?
    abstract fun failure(input: String): enumFail // failure type
    abstract fun completed(): Boolean? // has finished ?
    abstract fun clone(): AbsAlgPart

    /*
		 * override this to the number of mutations a mutation series can perform, so at
		 * least to 1 if you want mutations enabled.
		 */
    open fun getMutationLimit()=0
    /*
                 * override this to the number of mutations a mutation series can perform, so at
                 * least to 1 if you want mutations enabled.
                 */
    open fun myName()= this.javaClass.simpleName

}