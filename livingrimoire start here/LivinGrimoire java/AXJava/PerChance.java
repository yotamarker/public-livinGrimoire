package AXJava;

import LivinGrimoire.RegexUtil;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class PerChance {/*
 * extend me and add sentences and lists for parameters in the sentences in the
 * sub classes c'tor.
  replicate speech paterns, generate movie scripts or books and enjoy
 */
    protected ArrayList<String> sentences = new ArrayList<String>();
    protected Hashtable<String, UniqueItemSizeLimitedPriorityQueue> wordToList = new Hashtable<>();
    protected Random rand = new Random();
    private RegexUtil regexUtil = new RegexUtil();
    public PerChance() {
        super();
    }

    public String generateJoke() {
        int x = rand.nextInt(sentences.size());
        String result = sentences.get(x);
        return clearRecursion(result);
    }

    private String clearRecursion(String result) {
        int x;
        ArrayList<String> params = new ArrayList<String>();
        params = regexUtil.extractAllRegexes("(\\w+)(?= #)", result);
        for (String strI : params) {
            UniqueItemSizeLimitedPriorityQueue temp = wordToList.get(strI);
            String s1 = temp.getRNDElement();
            result = result.replace(strI + " #", s1);
        }
        if (!result.contains("#")) {
            return result;
        } else {
            return clearRecursion(result);
        }
    }
    public void addParam(String category, String value){
        if(wordToList.containsKey(category)){
            wordToList.get(category).add(value);
        }
    }
    public void addParam(AXKeyValuePair kv){
        if(wordToList.containsKey(kv.getKey())){
            wordToList.get(kv.getKey()).add(kv.getValue());
        }
    }
}