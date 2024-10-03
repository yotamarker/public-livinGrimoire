package Auxiliary_Modules;

public class Prompt {
    public AXKeyValuePair kv = new AXKeyValuePair();
    String prompt = "";
    String regex = "";
    public Prompt() {
        kv.setKey("default");
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    public Boolean process(String in1){
        kv.setValue(RegexUtil.extractRegex(regex,in1));
        return kv.getValue().isEmpty(); // is prompt still active?
    }

    public AXKeyValuePair getKv() {
        return kv;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
