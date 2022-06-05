import java.io.*;

public class sgdb
{
    public static int loginCheck(File usernames, File passwords, String user, String pass)
    {
        String line = "";
        // an int to check what line we're on
        int lineNum = 1;
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(usernames));
            // loop through the file until we find the username
            while (true)
            {
                line = in.readLine();
                if(line == null)
                {
                    // if we reach the end of the file with no username found, return 2
                    in.close();
                    return 2;
                }
                // if we find the username, check the password at the line number we're on
                if (line.equals(user))
                {
                    in.close();
                    in = new BufferedReader(new FileReader(passwords));
                    // jump to the password's line
                    for(int i = 0; i < lineNum; i++)
                    {
                        line = in.readLine();
                    }
                    //System.out.println(line);
                    // if the password is correct, return 0
                    if (line.equals(pass))
                    {
                        // right info
                        in.close();
                        return 0;
                    }
                    // if the pass is incorrect, return 1
                    else
                    {
                        // wrong pass
                        in.close();
                        return 1;
                    }
                }
                lineNum++;
            }
        }
        catch (Exception err)
        {
            System.out.println("Error: " + err);
        }
        // other error?
        return 3;
    }

    public static int userExistCheck(File usernames, String user)
    {
        String line = "";
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(usernames));
            while (true)
            {
                line = in.readLine();
                if(line == null)
                {
                    // if we reach the end of the file with no username found, return 0
                    in.close();
                    return 0;
                }
                if (line.equals(user))
                {
                    in.close();
                    // user already exists
                    return 1;
                }
            }
        }
        catch (Exception err)
        {
            System.out.println("Error: " + err);
        }
        // other error?
        return 2;
    }

    public static int addUser(File usernames, File passwords, File names, String user, String pass, String name)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(usernames, true));
            out.newLine();
            out.write(user);
            out.close();
            out = new BufferedWriter(new FileWriter(passwords, true));
            out.newLine();
            out.write(pass);
            out.close();
            out = new BufferedWriter(new FileWriter(names, true));
            out.newLine();
            out.write(name);
            out.close();
            // user added
            return 0;
        }
        catch (Exception err)
        {
            System.out.println("Error: " + err);
        }
        // other error?
        return 1;
    }
}
