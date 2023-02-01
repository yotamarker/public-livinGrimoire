package lgkt

import java.util.*


class FusionCera(private val algQueue: PriorityQueue<Algorithm>) : Cerabellum() {
    private var abort = 0
    fun setAbort(abort: Int) {
        this.abort = abort + 1
    }

    override fun act(ear: String, skin: String, eye: String): String {
        // TODO Auto-generated method stub
        val result = super.act(ear, skin, eye)
        abort--
        if (abort < 1) {
            super.setActive(false)
        } else {
            if (!super.isActive) {
                algQueue.remove(super.alg)
            }
        }
        return result
    }
}