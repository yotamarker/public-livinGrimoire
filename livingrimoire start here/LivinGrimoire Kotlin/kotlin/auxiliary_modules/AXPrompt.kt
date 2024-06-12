package auxiliary_modules

class AXPrompt {
    /*
    * example use:
    *
    *   // prompt1
        Prompt prompt1 = new Prompt();
        prompt1.kv.setKey("fruit");
        prompt1.setPrompt("do you prefer an apple, banana or grapes?");
        prompt1.setRegex("apple|banana|grapes");
        // prompt 2
        Prompt prompt2 = new Prompt();
        prompt2.kv.setKey("age");
        prompt2.setPrompt("how old are you??");
        prompt2.setRegex("\\d+(\\.\\d+)?");
        Scanner scanner = new Scanner(System.in);
        String in2 = "";
        AXPrompt axPrompt = new AXPrompt();
        axPrompt.addPrompt(prompt1);
        axPrompt.addPrompt(prompt2);
        axPrompt.activate();
        do{
            System.out.println(axPrompt.getPrompt());
            in2 = scanner.nextLine();
            axPrompt.process(in2);
            // extract keyvaluepair:
            AXKeyValuePair temp = axPrompt.getKv();
            // extract data: field, value
            if (!(temp == null)){
                System.out.println(temp.getValue());
            }
        }
        while (axPrompt.getActive());
    * */
    var active = false
    var index = 0
    var prompts = ArrayList<Prompt>()
    private var kv: AXKeyValuePair? = null
    fun addPrompt(p1: Prompt) {
        prompts.add(p1)
    }

    val prompt: String
        get() = if (prompts.isEmpty()) {
            ""
        } else prompts[index].prompt

    fun process(in1: String?) {
        if (prompts.isEmpty() || !active) {
            return
        }
        val b1 = prompts[index].process(in1!!)
        if (!b1) {
            kv = prompts[index].kv
            index++
        }
        if (index == prompts.size) {
            active = false
        }
    }

    fun getKv(): AXKeyValuePair? {
        if (kv == null) {
            return null
        }
        val temp = AXKeyValuePair()
        temp.key = kv!!.key
        temp.value = kv!!.value
        kv = null
        return temp
    }

    fun activate() {
        // reset
        active = true
        index = 0
    }

    fun deactivate() {
        // reset
        active = false
        index = 0
    }
}