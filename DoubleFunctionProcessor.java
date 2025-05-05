// DoubleFunctionProcessor.java

import java.util.function.DoubleFunction;

public class DoubleFunctionProcessor {
    private double value;


    public DoubleFunctionProcessor(double initValue) {
        this.value = initValue;
    }

    public synchronized void process(DoubleFunction<Double> function) {
        this.value = function.apply(this.value);
    }

    public synchronized double getValue() {
        return this.value;
    }
}
