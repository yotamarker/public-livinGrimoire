package AXJava;

public class AXLMorseCode extends AXLHousing{
    private AXMorseCode morse = new AXMorseCode();
    @Override
    public String decorate(String str1) {
        return morse.toMorse(str1);
    }
}
