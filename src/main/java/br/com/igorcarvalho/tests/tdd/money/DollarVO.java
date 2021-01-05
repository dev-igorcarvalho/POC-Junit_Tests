package br.com.igorcarvalho.tests.tdd.money;

public class DollarVO extends MoneyVO {

    public DollarVO(final double value) {
        super(value);
    }

    public DollarVO times(final double value) {
        return new DollarVO(this.getAmount() * value);
    }
}
