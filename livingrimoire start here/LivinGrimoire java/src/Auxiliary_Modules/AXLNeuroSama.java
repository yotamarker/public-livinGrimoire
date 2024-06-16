package Auxiliary_Modules;

public class AXLNeuroSama extends AXLHousing{
    private AXNeuroSama nyaa = new AXNeuroSama(3);

    @Override
    public String decorate(String str1) {
        return this.nyaa.decorate(str1);
    }
}
