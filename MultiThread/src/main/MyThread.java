package main;

public class MyThread extends Thread{

    private int i;

    public MyThread(int i)
    {
        this.i = i;
    }

    @Override
    public void run()
    {
        //super.run();
        System.out.println(i);
    }



}
