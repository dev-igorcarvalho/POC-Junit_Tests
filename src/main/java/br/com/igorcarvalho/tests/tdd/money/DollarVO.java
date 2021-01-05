package br.com.igorcarvalho.tests.tdd.money;

public class DollarVO extends MoneyVO {

    public DollarVO(final double value) {
        super(value);
    }

    @Override
    public MoneyVO times(double value) {
        return new DollarVO(this.amount * value);
    }
}
