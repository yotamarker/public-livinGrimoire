package auxiliary_modules

class Magic8Ball {
    var questions = Responder()
    var answers = Responder()

    init {
        // answers :
        // Affirmative Answers
        answers.addResponse("It is certain")
        answers.addResponse("It is decidedly so")
        answers.addResponse("Without a doubt")
        answers.addResponse("Yes definitely")
        answers.addResponse("You may rely on it")
        answers.addResponse("As I see it, yes")
        answers.addResponse("Most likely")
        answers.addResponse("Outlook good")
        answers.addResponse("Yes")
        answers.addResponse("Signs point to yes")
        // Non – Committal Answers
        answers.addResponse("Reply hazy, try again")
        answers.addResponse("Ask again later")
        answers.addResponse("Better not tell you now")
        answers.addResponse("Cannot predict now")
        answers.addResponse("Concentrate and ask again")
        // Negative Answers
        answers.addResponse("Don’t count on it")
        answers.addResponse("My reply is no")
        answers.addResponse("My sources say no")
        answers.addResponse("Outlook not so good")
        answers.addResponse("Very doubtful")
        // questions :
        questions = Responder(
            "will i",
            "can i expect",
            "should i",
            "may i",
            "is it a good idea",
            "will it be a good idea for me to",
            "is it possible",
            "future hold",
            "will there be"
        )
    }

    fun engage(ear: String): Boolean {
        if (ear.isEmpty()) {
            return false
        }
        return questions.strContainsResponse(ear)
    }

    fun reply(): String {
        return answers.aResponse
    }
}