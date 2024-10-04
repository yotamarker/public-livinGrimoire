package Auxiliary_Modules;

public class UniqueItemsPriorityQue extends LGFIFO<String>{
    // a priority queue without repeating elements
    @Override
    public void add(String item) {
        if(!super.contains(item)){
        super.add(item);}
    }
    public String peak() {
        String temp = super.peak();
        if (temp == null){
            return "";
        }
        return temp;
    }
    public Boolean strContainsResponse(String str){
        boolean result = false;
        for (String tempStr: this.elements) {
            if (str.contains(tempStr)){
                result = true;
                break;
            }
        }
        return result;
    }
}
