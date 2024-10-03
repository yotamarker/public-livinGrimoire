package Auxiliary_Modules;

public class ForcedLearn {
   // remembers key inputs because they start with keyword
   // also can dispense key inputs
    public String keyword = "say";
    private final LGFIFO<String> p1 = new LGFIFO<>();
    private int queLimit = 5;

    public int getQueLimit() {
        return queLimit;
    }

    public void setQueLimit(int queLimit) {
        if(!(queLimit>0)){return;}
        this.queLimit = queLimit;
    }
    public void input(String in1){
        if (keyword.equals(RegexUtil.extractRegex(enumRegexGrimoire.firstWord,in1))){
            p1.add(in1.replace(keyword,""));
            if (p1.size()>queLimit){
                p1.poll();
            }
        }
    }
    public String getRandomElement(){
        if (p1.isEmpty()){return "";}
        return p1.getRNDElement();
    }
    public Boolean contains(String item){
        return p1.contains(item);
    }
}
