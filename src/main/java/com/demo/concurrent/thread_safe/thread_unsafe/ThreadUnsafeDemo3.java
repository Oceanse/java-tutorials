package com.demo.concurrent.thread_safe.thread_unsafe;


/**
 * 当多个线程在操作同一个共享数据时，一个线程对多条语句只执行了一部分，
 * 还没有执行完，另一个线程参与进来执行。导致共享数据的错误
 *
 * 对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行
 *
 * @author epanhai*/
public class ThreadUnsafeDemo3 {


    public static void main(String[] args) {

        Account account=new Account(123,9000);

        //创建启动两个线程，两个线程拥有同一个Account对象
        new Thread(new Processor(account)).start();
        new Thread(new Processor(account)).start();

    }
}

class Processor implements Runnable{

    /**
     * 多个线程操作一个Account对象，Account对象就是共享资源，可能会出现线程安全问题
     */
    Account account;

    public Processor(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        //线程体去执行withdraw动作
        account.withdraw(1000);
        System.out.println("账号为 "+ account.getAccountId()+" 账户余额："+ account.getBalance());

    }
}

class Account {

    int accountId;

    /**
     *共享变量
     */
    double balance;

    public Account() {
    }

    public Account(int accountId, double balance) {
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
     *  多个线程同一时间都进入了这段代码会导致线程安全问题
     *  其中一个线程处理完balance-money，但是还没有更新余额this.setBalance(balance)
     *  此时第二线程进来就 balance-money，导致余额不正常
     * @param money
     */
    public void withdraw(double money)  {

        double balance=this.balance-money;
        try {
            //某个线程执行到这延迟(阻塞)2s，其它线程就会执行这段代码，
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setBalance(balance);
    }
}
