package auxiliary_modules

class SpiderSense {
    // enables event prediction
    private var spiderSense = false
    private val events = UniqueItemSizeLimitedPriorityQueue()
    private val alerts = UniqueItemSizeLimitedPriorityQueue()
    private var prev = ""
    fun addEvent(event: String): SpiderSense {
        // builder pattern
        events.add(event)
        return this
    }

    // input param  can be run through an input filter prior to this function
    // weather related data (sky state) only for example for weather events predictions
    fun learn(in1: String) {
        // simple prediction of an event from the events que :
        if (alerts.contains(in1)) {
            spiderSense = true
            return
        }
        // event has occured, remember what lead to it
        if (events.contains(in1)) {
            alerts.add(prev)
            return
        }
        // nothing happend
        prev = in1
    }

    fun getSpiderSense(): Boolean {
        // spider sense is tingling? event predicted?
        val temp = spiderSense
        spiderSense = false
        return temp
    }

    val alertsShallowCopy: ArrayList<String>
        get() =// return shallow copy of alerts list
            alerts.elements
    val alertsClone: ArrayList<String>
        get() {
            // return deep copy of alerts list
            val dc = DeepCopier()
            return dc.copyList(alerts.elements)
        }

    fun clearAlerts() {
        // this can for example prevent war, because say once a month or a year you stop
        // being on alert against a rival
        alerts.clear()
    }
    fun eventTriggered(in1: String): Boolean {
        return events.contains(in1)
    }
    // side note:
    // use separate spider sense for data learned by hear say in contrast to actual experience
    // as well as lies (false predictions)
}