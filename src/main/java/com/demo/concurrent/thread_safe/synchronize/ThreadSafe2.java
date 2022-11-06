package com.demo.concurrent.thread_safe.synchronize;


/**
 * 当多个线程在操作同一个共享数据时，一个线程对多条语句只执行了一部分，
 * 还没有执行完，另一个线程参与进来执行。导致共享数据的错误
 * <p>
 * 对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行
 *
 * @author epanhai
 */
public class ThreadSafe2 {


    public static void main(String[] args) {

        Accounts accounts = new Accounts(123, 9000);
        //创建启动两个线程，两个线程拥有同一个Account对象
        new Thread(new Processors(accounts)).start();
        new Thread(new Processors(accounts)).start();

    }
}

class Processors implements Runnable {

    /**
     * 多个线程操作一个Account对象，Account对象就是共享资源，可能会出现线程安全问题
     */
    Accounts accounts;

    public Processors(Accounts accountcs) {
        this.accounts = accountcs;
    }

    @Override
    public void run() {
        accounts.withdraw(1000);
        System.out.println("账号为 " + accounts.getAccountId() + " 账户余额：" + accounts.getBalance());

    }
}

class Accounts {

    int accountId;
    double balance;

    public Accounts() {
    }

    public Accounts(int accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 多个线程同一时间都进入了这段代码会导致线程安全问题
     * 其中一个线程处理完balance-money，但是还没有更新余额this.setBalance(balance)
     * 此时第二线程进来就 balance-money，导致余额不正常
     * 使用synchronized(this)同步锁保证同一时刻只有一个线程操作
     * this是指Accounts对象，这里多个线程共享同一个Accounts对象，所以this对象可以保证锁的唯一性，因此这里可以用this
     */
    public void withdraw(double money) {
        synchronized (this) {
            double balance = this.balance - money;

            try {
                //若没加锁，某个线程执行到这会阻塞(失去cpu时间片处于阻塞状态，一旦达到睡眠时间，进入就绪状态)1s，然后切换到其它线程执行这段代码
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.setBalance(balance);
        }
    }


    /**
     *  上面synchronized可以用在方法上，这样就同步了方法体中的所有代码，使得程序执行效率降低；
     *  synchronize用在代码块上更加灵活高效
     * @param money
     */
  public synchronized void withdraw2(double money)  {

            double balance=this.balance-money;

            try {
                Thread.sleep(2000);//某个线程执行到这延迟(阻塞)2s，其它线程就会执行这段代码，
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.setBalance(balance);
    }


}

