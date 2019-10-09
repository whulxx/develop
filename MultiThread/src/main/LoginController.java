package main;

public class LoginController {
    private static String usernameRef;
    private static String passwordRef;
    public static void doPost(String username, String password)
    {
        try{
            usernameRef = username;
            if(username.equals("a"))
            {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username="+username+" password="+password);


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
