package Auxiliary_Modules;

import LivinGrimoire.Algorithm;
import LivinGrimoire.Skill;

public class AXFriend {
    private String myName = "chi"; // default name
    private String friend_name = "null";
    private Boolean needsFriend = true;
    private Skill diSkillUtils = new Skill();
    private TrgTolerance active = new TrgTolerance(10);
    private Boolean friendIsActive = false;

    public AXFriend(int tolerance) {
        // recommended 11
        active = new TrgTolerance(tolerance);
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        // make changeable on 1st day
        this.myName = myName;
    }

    public void reset(){
        // should reset once a month
        friend_name = "null";
        needsFriend = true;
        active.disable();
        friendIsActive = false;
    }

    public String getFriend_name() {
        return friend_name;
    }

    public Algorithm friendHandShake() {
        // engage after reset() or at certain time of day if needsFriend, with snooze
        return diSkillUtils.simpleVerbatimAlgorithm("friend_request", "i am " + myName);
    }

    public Boolean getFriendIsActive() {
        return friendIsActive;
    }

    public Algorithm handle(String ear, String skin, String eye) {
        if(needsFriend && ear.contains("i am ")){
            // register new friend
            active.reset();
            friendIsActive = active.trigger();
            friend_name = ear.replace("i am ", "");
            needsFriend = false;
            return friendHandShake();
        }
        if(ear.contains(myName)){active.reset();}
        friendIsActive = active.trigger();
        return null;
    }
}
