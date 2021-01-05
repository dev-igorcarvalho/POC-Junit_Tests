package br.com.igorcarvalho.tests.tdd.money;

public class DollarVO {

    private final double amount;

    public DollarVO(final double value) {
        this.amount=value;
    }

    public DollarVO times(final double value) {
        return new DollarVO(this.amount*value);
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DollarVO dollarVO = (DollarVO) o;

        return Double.compare(dollarVO.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }
}
