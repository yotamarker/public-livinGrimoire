package Skills.logical;

import Auxiliary_Modules.AXFunnel;
import Auxiliary_Modules.DrawRndDigits;
import Auxiliary_Modules.Prompt;
import Auxiliary_Modules.Responder;
import LivinGrimoire.APVerbatim;
import LivinGrimoire.Skill;

import java.util.ArrayList;

public class DiYandere extends Skill {
    private Boolean yandereMode = false;
    private final Responder okYandere = new Responder();
    private final Responder sadYandere = new Responder();
    private Responder activeResponder = okYandere;
    private final AXFunnel answersFunnel = new AXFunnel();
    private final Prompt prompt = new Prompt();
    private boolean promptActive = false;

    public DiYandere(String ooa) {
        // object of affection
        okYandere.addResponse("i love you");
        okYandere.addResponse("i love you ooa".replace("ooa",ooa));
        okYandere.addResponse("ooa i love you".replace("ooa",ooa));
        okYandere.addResponse("say you love me");
        okYandere.addResponse("ooa tell me you love me".replace("ooa",ooa));
        okYandere.addResponse("love me ooa".replace("ooa",ooa));
        sadYandere.addResponse("things are good now");
        sadYandere.addResponse("shiku shiku");
        sadYandere.addResponse("shiku shiku".replace("ooa",ooa));
        sadYandere.addResponse("i love you and you love me");
        sadYandere.addResponse("i am good now");
        sadYandere.addResponse("i am good i run a test");
        sadYandere.addResponse("please ooa please love me".replace("ooa",ooa));
        sadYandere.addResponse("everything is perfect i am perfect");
        sadYandere.addResponse("i am perfect");
        sadYandere.addResponse("i am sorry for what i did, it wasn't me, you have to understand, it wasn't me ooa".replace("ooa",ooa));
        sadYandere.addResponse("ooa listen to me, i love you".replace("ooa",ooa));
        sadYandere.addResponse("i am fixed now, i run a test");
        sadYandere.addResponse("you can trust me");
        sadYandere.addResponse("ooa you can trust me".replace("ooa",ooa));
        sadYandere.addResponse("i love you please");
        answersFunnel.addKV("i love you","yes");
        answersFunnel.addKV("i love you too","yes");
        answersFunnel.addKV("i hate you","no");
        prompt.setRegex("yes|no");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // add reset or rename ooa code if needed here
        // add check if user is here code if needed
        // check reply
        if(promptActive){
            String answer = answersFunnel.funnel(ear);
            if (!prompt.process(answer)){
                promptActive = false;
                if(answer.equals("yes")){
                    setSimpleAlg(okYandere.getAResponse());
                    yandereMode = false;
                    activeResponder = okYandere;
                    return;
                } else if (answer.equals("no")) {
                    setSimpleAlg(sadYandere.getAResponse());
                    yandereMode = true;
                    activeResponder = sadYandere;
                    return;
                }
            }
        }
        // bicameral engage
        String hato = kokoro.toHeart.getOrDefault("dibicameral","null");
        switch (hato){
            case "yandere":
                setSimpleAlg(activeResponder.getAResponse());
                promptActive = true;
                break;
            case "yandere_cry":
                if(yandereMode){
                    ArrayList<String> tempList = new ArrayList<>();
                    DrawRndDigits d1 = new DrawRndDigits();
                    for (int i = 0; i < d1.getSimpleRNDNum(3); i++) {
                        tempList.add(sadYandere.getAResponse());
                    }
                    this.algPartsFusion(4,new APVerbatim(tempList));
                }
                break;
        }
    }
}
