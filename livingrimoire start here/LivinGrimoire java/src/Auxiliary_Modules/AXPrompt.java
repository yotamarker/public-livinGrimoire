package Auxiliary_Modules;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class AXPrompt {
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
    Boolean isActive = false;
    int index = 0;
    ArrayList<Prompt> prompts = new ArrayList<Prompt>();
    AXKeyValuePair kv = null;
    public void addPrompt(Prompt p1){
        prompts.add(p1);
    }
    public String getPrompt(){
        if (prompts.isEmpty()){return "";}
        return prompts.get(index).getPrompt();
    }
    public void process(String in1){
        if (prompts.isEmpty() || !isActive){return;}
        Boolean b1= prompts.get(index).process(in1);
        if (!b1){
            kv = prompts.get(index).getKv();
            index++;
        }
        if(index == prompts.size()){isActive = false;}
    }

    public Boolean getActive() {
        return isActive;
    }

    public AXKeyValuePair getKv() {
        if (kv == null){
            return null;
        }
        AXKeyValuePair temp = new AXKeyValuePair();
        temp.setKey(kv.getKey());
        temp.setValue(kv.getValue());
        kv = null;
        return temp;
    }
    public void activate(){
        // reset
        isActive = true;
        index = 0;
    }
    public void deactivate(){
        // reset
        isActive = false;
        index = 0;
    }
}
