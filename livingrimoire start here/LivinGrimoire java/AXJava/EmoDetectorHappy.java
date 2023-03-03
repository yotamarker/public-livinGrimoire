package AXJava;

public class EmoDetectorHappy extends Responder{
    public EmoDetectorHappy() {
        super("good","awesome","great","wonderful","sweet","happy");
    }
    public Boolean isHappy(String str){
        return super.strContainsResponse(str);
    }
}
