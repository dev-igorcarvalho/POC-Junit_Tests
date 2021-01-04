package br.com.igorcarvalho.tests.tdd.money;

public class Dollar {

    public double amount;

    public Dollar(double value) {
        this.amount=value;
    }

    public void times(double value) {
        this.amount*=value;
    }
}
