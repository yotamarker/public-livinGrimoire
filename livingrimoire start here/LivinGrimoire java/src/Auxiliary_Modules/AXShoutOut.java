package Auxiliary_Modules;

public class AXShoutOut {
    private Boolean isActive = false;
    public Responder handshake = new Responder();
    public void activate(){
        // make engage-able
        isActive = true;
    }
    public Boolean engage(String ear){
        if (ear.isEmpty()) {return false;}
        if (isActive){
            if (handshake.strContainsResponse(ear)){
                isActive = false;return true; // shout out was replied!
            }
        }
        // unrelated reply to shout out, shout out context is outdated
        isActive = false;
        return false;
    }
}
