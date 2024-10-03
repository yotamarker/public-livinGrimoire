package Auxiliary_Modules;

public class AXPassword {
    // code # to open the gate
    // while gate is open, code can be changed with: code new_number
    private Boolean isOpen = false;
    private int maxAttempts = 3;
    private int loginAttempts = maxAttempts;
    private int code = 0;
    public Boolean codeUpdate(String ear){
        // while the gate is toggled on, the password code can be changed
        if(!isOpen){return false;}
        if(ear.contains("code")){
            String temp = RegexUtil.extractRegex(enumRegexGrimoire.integer,ear);
            if(!temp.isEmpty()){code = Integer.parseInt(temp);
            return true;}
        }
        return false;
    }
    public void openGate(String ear) {
        if (ear.contains("code") && (loginAttempts > 0)) {
            String noCode = RegexUtil.extractRegex(enumRegexGrimoire.integer, ear);
            if (noCode.isEmpty()) {
                return;
            }
            int tempCode = Integer.parseInt(noCode);
            if (tempCode == code) {
                loginAttempts = maxAttempts;
                isOpen = true;
            } else {
                loginAttempts--;
            }
        }
    }
    public Boolean isOpen() {
        return isOpen;
    }
    public void resetAttempts(){
        // should happen once a day or hour to prevent hacking
        loginAttempts = maxAttempts;
    }

    public int getLoginAttempts() {
        // return remaining login attempts
        return loginAttempts;
    }
    public void closeGate(){
        isOpen=false;
    }
    public void closeGate(String ear){
        if(ear.contains("close")){isOpen=false;}
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public int getCode() {
        if(isOpen){
        return code;}
        return -1;
    }
    public void randomizeCode(int lim, int minimumLim){
        // event feature
        code = new DrawRnd().getSimpleRNDNum(lim) + minimumLim;
    }
    public int getCodeEvent(){
        // event feature
        // get the code during weekly/monthly event after it has been randomized
        return code;
    }
}
