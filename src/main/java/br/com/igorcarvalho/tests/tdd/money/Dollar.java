package br.com.igorcarvalho.tests.tdd.money;

public class Dollar {

    public final double amount;

    public Dollar(double value) {
        this.amount=value;
    }

    public Dollar times(double value) {
        return new Dollar(this.amount*value);
    }
}
