package br.com.igorcarvalho.tests.tdd.money;

public class FrancVO extends MoneyVO {

    public FrancVO(final double value) {
        super(value);
    }

    @Override
    public MoneyVO times(double value) {
        return new FrancVO(this.getAmount() * value);
    }
}
