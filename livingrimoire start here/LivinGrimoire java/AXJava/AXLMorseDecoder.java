package AXJava;

public class AXLMorseDecoder extends AXLHousing{
    private AXMorseCode morse = new AXMorseCode();
    @Override
    public String decorate(String str1) {
        return morse.toHuman(str1);
    }
}
