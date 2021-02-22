package oose.dea.bankdemo;

public class ParallelOpnemen implements Runnable{
    private Bank bank;
    private int amount;

    public ParallelOpnemen(Bank bank, int amount){
        this.bank= bank;
        this.amount =amount;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        bank.neemOp(amount);
    }
}
