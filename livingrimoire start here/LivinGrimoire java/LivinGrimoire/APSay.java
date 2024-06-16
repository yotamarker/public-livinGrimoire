package LivinGrimoire;
/* it speaks something x times
 * a most basic skill.
 * also fun to make the chobit say what you want
 * */
public class APSay extends Mutatable{
    protected String param;
    private int at;

    public APSay(int repetitions, String param) {
        super();
        if (repetitions > 10) {
            repetitions = 10;
        }
        this.at = repetitions;
        this.param = param;
    }

    @Override
    public String action(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        String axnStr = "";
        if (this.at > 0) {
            if (!ear.equalsIgnoreCase(param)) {
                axnStr = param;
                at--;
            }
        }
        return axnStr;
    }
    @Override
    public Boolean completed() {
        // TODO Auto-generated method stub
        return at < 1;
    }
}
