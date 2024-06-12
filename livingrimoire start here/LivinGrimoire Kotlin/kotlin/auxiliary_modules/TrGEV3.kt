package auxiliary_modules

open class TrGEV3 {
    // advanced boolean gates with internal logic
    // these ease connecting common logic patterns, as triggers
    open fun reset() {}
    fun input(ear: String, skin: String, eye: String) {}
    open fun trigger(): Boolean {
        return false
    }
}