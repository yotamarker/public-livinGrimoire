package Auxiliary_Modules;

public class AXLMorseCode extends AXLHousing{
    private final AXMorseCode morse = new AXMorseCode();
    @Override
    public String decorate(String str1) {
        return morse.toMorse(str1);
    }
}
