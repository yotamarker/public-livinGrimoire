package Skills.logical;

import Auxiliary_Modules.*;
import LivinGrimoire.Skill;

import java.util.ArrayList;

public class DiHabit extends Skill {
    /*1 *habit*

set: i should x

get: random habit

engage : x completed

clear : clear habits

2 *bad habit*

set: i must not x

get: random bad habit

engage: x completed

clear: clear bad habits

3 *dailies*

set: i out to x

get: random daily

engage: x completed

clear: clear dailies

4 *preps or weekend tasks*

set: i have to x

get: random prep or random weekend

engage: x completed

clear: clear preps or clear weekends

5 *expirations*

set: i got to x

get: random expiration or random expirations
(will return all of then)

engage: none

clear: clear expirations

6 *to do*

set: i need to

get: random task (for new ToDos)
or random to do (for completed to dos)

engage: auto

clear: clear tasks or clear task or clear to do

note the gamification modules can be shallow referenced into another skill
and used to "unlock" skills.
*/
    /*setter params*/
    // habit params
    private UniqueItemSizeLimitedPriorityQueue habitsPositive = new UniqueItemSizeLimitedPriorityQueue();
    private AXCmdBreaker habitP = new AXCmdBreaker("i should");
    private String temp = "";
    // bad habits
    private UniqueItemSizeLimitedPriorityQueue habitsNegative = new UniqueItemSizeLimitedPriorityQueue();
    private AXCmdBreaker habitN = new AXCmdBreaker("i must not");
    // dailies
    private UniqueItemSizeLimitedPriorityQueue dailies = new UniqueItemSizeLimitedPriorityQueue();
    private AXCmdBreaker dailyCmdBreaker = new AXCmdBreaker("i out to");
    // weekends
    private UniqueItemSizeLimitedPriorityQueue weekends = new UniqueItemSizeLimitedPriorityQueue();
    private AXCmdBreaker weekendCmdBreaker = new AXCmdBreaker("i have to");
    // expirations
    private UniqueItemSizeLimitedPriorityQueue expirations = new UniqueItemSizeLimitedPriorityQueue();
    private AXCmdBreaker expirationsCmdBreaker = new AXCmdBreaker("i got to");
    // to-do list
    private TODOListManager todo = new TODOListManager(5);
    private AXCmdBreaker toDoCmdBreaker = new AXCmdBreaker("i need to");
    private AXCmdBreaker clearCmdBreaker = new AXCmdBreaker("clear");
    //getter param
    private AXCmdBreaker getterCmdBreaker = new AXCmdBreaker("random");
    private AXStrOrDefault strOrDefault = new AXStrOrDefault();
    // gamification modules for shallow ref in other skills
    private AXGamification gamification = new AXGamification();
    private AXGamification punishments = new AXGamification();

    public DiHabit() {
        habitsPositive.setLimit(15);
        habitsNegative.setLimit(5);
        dailies.setLimit(3);
        weekends.setLimit(3);
        expirations.setLimit(3);
    }

    public AXGamification getGamification() {
        return gamification;
    }

    public AXGamification getPunishments() {
        return punishments;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.isEmpty()){return;}
        // setters
        if (ear.contains("i")){
            temp = habitP.extractCmdParam(ear);
            if (!temp.isEmpty()){
                habitsPositive.add(temp);
                temp = "";
                setVerbatimAlg(4,"habit registered");
                return;
            }
            temp = habitN.extractCmdParam(ear);
            if (!temp.isEmpty()){
                habitsNegative.add(temp);
                temp = "";
                setVerbatimAlg(4,"bad habit registered");
                return;
            }
            temp = dailyCmdBreaker.extractCmdParam(ear);
            if (!temp.isEmpty()){
                dailies.add(temp);
                temp = "";
                setVerbatimAlg(4,"daily registered");
                return;
            }
            temp = weekendCmdBreaker.extractCmdParam(ear);
            if (!temp.isEmpty()){
                weekends.add(temp);
                temp = "";
                setVerbatimAlg(4,"prep registered");
                return;
            }
            temp = expirationsCmdBreaker.extractCmdParam(ear);
            if (!temp.isEmpty()){
                expirations.add(temp);
                temp = "";
                setVerbatimAlg(4,"expiration registered");
                return;
            }
            temp = toDoCmdBreaker.extractCmdParam(ear);
            if (!temp.isEmpty()){
                todo.addTask(temp);
                temp = "";
                setVerbatimAlg(4,"task registered");
                return;
            }
        }
        // getters
        temp = getterCmdBreaker.extractCmdParam(ear);
        if (!temp.isEmpty()){
            switch (temp){
                case "habit":
                    setVerbatimAlg(4,strOrDefault.getOrDefault(habitsPositive.getRNDElement(),"no habits registered"));
                    return;
                case "bad habit":
                    setVerbatimAlg(4,strOrDefault.getOrDefault(habitsNegative.getRNDElement(),"no bad habits registered"));
                    return;
                case "daily":
                    setVerbatimAlg(4,strOrDefault.getOrDefault(dailies.getRNDElement(),"no dailies registered"));
                    return;
                case "weekend": case "prep":
                    setVerbatimAlg(4,strOrDefault.getOrDefault(weekends.getRNDElement(),"no preps registered"));
                    return;
                case "expirations":case "expiration":
                    if(expirations.getAsList().isEmpty()){
                        setVerbatimAlg(4,"no expirations registered");
                        return;
                    }
                    setVerbatimAlgFromList(4,expirations.getAsList());
                    return;
                case "task":
                    setVerbatimAlg(4,strOrDefault.getOrDefault(todo.getTask(),"no new tasks registered"));
                    return;
                case "to do":
                    setVerbatimAlg(4,strOrDefault.getOrDefault(todo.getOldTask(),"no tasks registered"));
                    return;
            }
        }
        // engagers
        if(ear.contains("completed")){
            if (!(strContainsList(ear,habitsPositive.getAsList()).isEmpty())){
                gamification.increment();
                setVerbatimAlg(4,"good boy");
                return;
            }
            if (!(strContainsList(ear,habitsNegative.getAsList()).isEmpty())){
                punishments.increment();
                setVerbatimAlg(4,"bad boy");
                return;
            }
            if (!(strContainsList(ear,dailies.getAsList()).isEmpty())){
                gamification.increment();
                setVerbatimAlg(4,"daily engaged");
                return;
            }
            if (!(strContainsList(ear,weekends.getAsList()).isEmpty())){
                setVerbatimAlg(4,"prep engaged");
                return;
            }
            // expiration gamification redacted
        }
        // clear specific field
        switch(ear) {
            case "clear habits":
                habitsPositive.clear();
                setVerbatimAlg(4,"habits cleared");
                break;
            case "clear bad habits":
                habitsNegative.clear();
                setVerbatimAlg(4,"bad habits cleared");
                break;
            case "clear dailies":
                dailies.clear();
                setVerbatimAlg(4,"dailies cleared");
                break;
            case "clear preps": case "clear weekends":
                weekends.clear();
                setVerbatimAlg(4,"preps cleared");
                break;
            case "clear expirations":
                expirations.clear();
                setVerbatimAlg(4,"expirations cleared");
                break;
            case "clear tasks":case "clear task":case "clear to do":
                todo.clearAllTasks();
                setVerbatimAlg(4,"tasks cleared");
                break;
            case "clear all habits":
                habitsPositive.clear();
                habitsNegative.clear();
                dailies.clear();
                weekends.clear();
                expirations.clear();
                todo.clearAllTasks();
                setVerbatimAlg(4,"all habits cleared");
                break;
            default:
                if (ear.contains("clear")){
                    temp = clearCmdBreaker.extractCmdParam(ear);
                    if(todo.containsTask(temp)){
                        todo.clearTask(temp);
                        setVerbatimAlg(4,temp +" task cleared");
                        temp = "";
                    }
                }
        }
    }
}
