package br.com.igorcarvalho.tests.examples.tdd.money;

public class MoneyVO {

    private final double amount;
    private final CurrencyTypeEnum currency;

    public MoneyVO(final double value, final CurrencyTypeEnum currency) {
        this.amount = value;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public CurrencyTypeEnum currency() {
        return currency;
    }

    public MoneyVO times(final double value) {
        return new MoneyVO(this.amount*value,this.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyVO moneyVO = (MoneyVO) o;

        if (Double.compare(moneyVO.amount, amount) != 0) return false;
        return currency == moneyVO.currency;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MoneyVO{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
