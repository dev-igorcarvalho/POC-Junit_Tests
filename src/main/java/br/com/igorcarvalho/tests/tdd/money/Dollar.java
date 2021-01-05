package br.com.igorcarvalho.tests.tdd.money;

public class Dollar {

    private final double amount;

    public Dollar(final double value) {
        this.amount=value;
    }

    public Dollar times(final double value) {
        return new Dollar(this.amount*value);
    }

    public double getAmount() {
        return amount;
    }
}
