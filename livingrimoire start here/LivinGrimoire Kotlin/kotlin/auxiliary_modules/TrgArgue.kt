package auxiliary_modules

/*example use
 *      TrgArgue trgArgue = new TrgArgue();
        trgArgue.contextCommands.add("tell me the time");
        trgArgue.commands.add("please");trgArgue.commands.add("but");
        System.out.println(trgArgue.engageCommand("hello")); // 0
        System.out.println("counter " + trgArgue.getCounter()); // 0
        System.out.println(trgArgue.engageCommand("tell me the time")); // 1
        System.out.println("counter " + trgArgue.getCounter()); // 0
        System.out.println(trgArgue.engageCommand("tell me the time")); // 1
        System.out.println("counter " + trgArgue.getCounter()); // 1
        System.out.println(trgArgue.engageCommand("please tell me")); // 2
        System.out.println("counter " + trgArgue.getCounter()); // 2
        System.out.println(trgArgue.engageCommand("but need to know the time")); // 2
        System.out.println("counter " + trgArgue.getCounter()); // 3
        System.out.println(trgArgue.engageCommand("whatever")); // 0
        System.out.println("counter " + trgArgue.getCounter()); // 0
 *  */
class TrgArgue {
    var commands = UniqueItemSizeLimitedPriorityQueue()
    var contextCommands = UniqueItemSizeLimitedPriorityQueue()
    private var trgTolerance = false

    // (breaking point of argument can be established (argue till counter == N))
    var counter = 0 // count argues/requests made in succession
        private set

    fun engageCommand(s1: String): Int {
        // 0-> no engagement
        // 1-> engaged boolean gate (request made)
        // 2-> engaged argument : consecutive request made (request in succession after a previous request)
        if (s1.isEmpty()) {
            return 0
        }
        if (contextCommands.contains(s1)) {
            if (trgTolerance) {
                counter++
            }
            trgTolerance = true
            return 1
        }
        return if (trgTolerance) {
            if (!commands.strContainsResponse(s1)) {
                trgTolerance = false
                counter = 0
                0
            } else {
                counter++
                2
            }
        } else 0
    }

    fun disable() {
        // context commands are disabled till next engagement with a command
        trgTolerance = false
    }
}