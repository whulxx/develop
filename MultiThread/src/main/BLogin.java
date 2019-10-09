package main;

public class BLogin extends Thread{
    @Override
    public void run()
    {
        LoginController.doPost("b","bb");
    }
}
