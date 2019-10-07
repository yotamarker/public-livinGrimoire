package chobit;


import java.util.Hashtable;

// dirty talk
public class APDirtyTalk extends AbsAlgPart {
	private Hashtable<String, String> hashtable = new Hashtable<>(); // script for dirty talking
    private String indexConvo = "start";
    private String kowase = "ok next";
    private Boolean isCompleted = false;

    @Override

	public String action(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        String axnStr = "";
		if (ear.equals(kowase)) {
            isCompleted = true;
		} else if (hashtable.containsKey(ear)) {
			indexConvo = ear;
            axnStr = hashtable.get(indexConvo);
        } else if (hashtable.containsKey(indexConvo)) {
            indexConvo = hashtable.get(indexConvo);
            axnStr = indexConvo;
        } else {
            isCompleted = true;
        }
        return axnStr;
    }

    public APDirtyTalk(Hashtable<String, String> hashtable) {
        super();
        this.hashtable = hashtable;
    }

    @Override
    public enumFail failure(String input) {
        // TODO Auto-generated method stub
        return enumFail.ok;
    }

    @Override
    public Boolean completed() {
        // TODO Auto-generated method stub
        return isCompleted;
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub
        return new APDirtyTalk((Hashtable) this.hashtable.clone());
    }

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

}
