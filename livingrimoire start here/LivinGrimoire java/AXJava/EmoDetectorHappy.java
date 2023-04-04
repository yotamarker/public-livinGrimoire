package AXJava;

public class EmoDetectorHappy extends Responder{
    public EmoDetectorHappy() {
        super("good","happy");
    }
    public Boolean isHappy(String str){
        return super.strContainsResponse(str);
    }
}
