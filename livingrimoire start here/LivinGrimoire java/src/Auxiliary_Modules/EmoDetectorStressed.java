package Auxiliary_Modules;

public class EmoDetectorStressed extends Responder{
    public EmoDetectorStressed() {
        super("ouch","help","dough");
    }
    public Boolean isHappy(String str){
        return super.strContainsResponse(str);
    }
}
