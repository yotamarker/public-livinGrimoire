package Auxiliary_Modules;

public class Strategy {
    private final UniqueResponder allStrategies;
    private final int strategiesLim;
    private final UniqueItemSizeLimitedPriorityQueue activeStrategy;

    // Constructor
    public Strategy(UniqueResponder allStrategies, int strategiesLim) {
        this.allStrategies = allStrategies;
        this.strategiesLim = strategiesLim;
        this.activeStrategy = new UniqueItemSizeLimitedPriorityQueue();
        this.activeStrategy.setLimit(strategiesLim);
        for (int i = 0; i < this.strategiesLim; i++) {
            this.activeStrategy.add(this.allStrategies.getAResponse());
        }
    }

    // Evolve strategies
    public void evolveStrategies() {
        for (int i = 0; i < this.strategiesLim; i++) {
            this.activeStrategy.add(this.allStrategies.getAResponse());
        }
    }

    // Get strategy
    public String getStrategy() {
        return this.activeStrategy.getRNDElement();
    }
}

