package Auxiliary_Modules;

public class Responder1Word {
    // learns 1 word inputs
    // outputs learned recent words
    UniqueItemSizeLimitedPriorityQueue q= new UniqueItemSizeLimitedPriorityQueue();

    public Responder1Word() {
        q.add("chi");
        q.add("gaga");
        q.add("gugu");
        q.add("baby");
    }
    public void listen(String ear){
        if (!(ear.contains(" ") || ear.isEmpty())){
            q.add(ear);
        }
    }
    public String getAResponse(){
        return q.getRNDElement();
    }
    public Boolean contains(String ear){
        return q.contains(ear);
    }
}
