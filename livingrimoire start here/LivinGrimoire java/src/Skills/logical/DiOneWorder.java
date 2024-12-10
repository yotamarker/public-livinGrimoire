package Skills.logical;

import Auxiliary_Modules.PercentDripper;
import LivinGrimoire.Skill;

public class DiOneWorder extends Skill {
    private String cry = "chi ";
    private String toggler = "chi";
    private PercentDripper drip = new PercentDripper();
    private Boolean mode = false;

    public void setCry(String cry) {
        this.cry = cry + " ";
    }

    public void setDrip(PercentDripper drip) {
        this.drip = drip;
    }

    public void setToggler(String toggler) {
        this.toggler = toggler;
    }
    public void setDripPercent(int n){
        drip.setLimis(n);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if(ear.isEmpty()){return;}
        if(ear.equals(toggler)){
            mode = !mode;
            setSimpleAlg("toggled");
            return;
        }
        if(mode && drip.drip()){
            setSimpleAlg(convertToChi(ear));
        }
    }
    public String convertToChi(String input) {
        // Split the input string into words
        String[] words = input.split("\\s+"); // Assumes words are separated by spaces

        // Initialize an empty result string
        StringBuilder result = new StringBuilder();

        // Iterate through each word
        for (String word : words) {
            // Append "chi" to the result
            result.append(cry);
        }

        // Remove the trailing space
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    @Override
    public String skillNotes(String param) {
        if (param.equals("triggers")) {
            return "say chi to toggle skill";
        }
        return "talks like a cute pet";
    }
}
