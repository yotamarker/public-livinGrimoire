package Auxiliary_Modules;

public class AXStringSplit {
    // may be used to prepare data before saving or after loading
    // the advantage is less data fields. for example: {skills: s1_s2_s3}
    private String spChar = "_";

    public void setSpChar(String spChar) {
        this.spChar = spChar;
    }
    public String[] split(String s1){
        return s1.split(spChar);
    }
    public String stringBuilder(String[] sArr){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sArr.length - 1; i++) {
            sb.append(sArr[i]);
            sb.append(this.spChar);
        }
        sb.append(sArr[sArr.length - 1]);
        return sb.toString();
    }
}
