package lgkt

class Brain(private val MVC: actionable, private val chi: thinkable) {
    fun doIt(ear: String, skin: String, eye: String) {
        val result = chi.think(ear, skin, eye)
        MVC.act(result)
    }
}