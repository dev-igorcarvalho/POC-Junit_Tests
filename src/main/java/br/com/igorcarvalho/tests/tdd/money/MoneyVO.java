package br.com.igorcarvalho.tests.tdd.money;

public abstract class MoneyVO {

    protected final double amount;

    protected MoneyVO(final double value) {
        this.amount = value;
    }

    public double getAmount() {
        return amount;
    }

    public abstract MoneyVO times(final double value);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyVO money = (MoneyVO) o;

        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }
}
