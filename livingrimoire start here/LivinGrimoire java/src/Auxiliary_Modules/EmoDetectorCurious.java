package Auxiliary_Modules;

public class EmoDetectorCurious extends Responder{
    public EmoDetectorCurious() {
        super("why","where","when","how","who","which","whose");
    }
    public Boolean isCurious(String str){
        return super.strContainsResponse(str);
    }
}
