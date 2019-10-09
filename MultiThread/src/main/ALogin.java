package main;

public class ALogin extends Thread{
    @Override
    public void run()
    {
        LoginController.doPost("a","aa");
    }
}
