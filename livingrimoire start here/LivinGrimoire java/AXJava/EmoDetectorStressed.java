package AXJava;

public class EmoDetectorStressed extends Responder{
    public EmoDetectorStressed() {
        super("ouch","help","fudge sticks","dough");
    }
    public Boolean isHappy(String str){
        return super.strContainsResponse(str);
    }
}
