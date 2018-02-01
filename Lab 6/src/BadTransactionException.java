public class BadTransactionException extends Exception {

    public int badAmountMoney;

    public BadTransactionException(int badAmount){
        super("Invalid amount of money: " + badAmount);
        badAmountMoney = badAmount;
    }
}
