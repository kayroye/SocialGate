import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class sgdbCSV 
{
    // Initializing my variables for use in this file and the main file
    public static String id;
    public static List<String> usernames = new ArrayList<String>();
    // This 2D arraylist is used to store the data from the csv file 
    // It is a list of a list of strings, where each list is a row
    public static List<List<String>> currentDb = new ArrayList<List<String>>();

    public static ArrayList<Integer> chosen = new ArrayList<Integer>();
    public static int random;
    public static int lines;

    // This method is used to read the CSV file and check a username and password against the database
    public static int loginCheck(File db, String user, String pass)
    {
        String row = "";
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader csvReader = new BufferedReader(new FileReader(db));
            // While the current line is not empty, read the file line by line
            while ((row = csvReader.readLine()) != null)
            {
                String [] data = row.split(",");
                // If the given user matches the current line's user...
                if (data[0].equals(user))
                {
                    // If the given password matches the current line's password...
                    if (data[1].equals(pass))
                    {
                        // Log the user in and return a success code
                        csvReader.close();
                        System.out.println("Logging in user " + user + "\nName: " + data[2] + "\nId: " + data[3]);
                        // Set the user's status to Online
                        setStatus(db, user, "Online");
                        return 0;
                    }
                    else
                    {
                        // If the password is incorrect, return a failure code
                        csvReader.close();
                        return 1;
                    }
                }
            }
            // If the user is not found within the entire database, return a failure code
            csvReader.close();
            return 2;
        }
        catch (Exception err)
        {
            // If an error occurs, return a failure code
            System.out.println("Error: " + err);
            return 2;
        }
    }

    // This method is used to read the CSV file and check if a username exists within the database
    public static int userExistCheck(File db, String user)
    {
        String row = "";
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader csvReader = new BufferedReader(new FileReader(db));
            while ((row = csvReader.readLine()) != null)
            {
                String [] data = row.split(",");
                // If the given user matches the current line's user...
                if (data[0].equals(user))
                {
                    // Return a success code (user exists)
                    csvReader.close();
                    return 1;
                }
            }
            // If the user is not found within the entire database, return a failure code
            csvReader.close();
            return 0;
        }
        catch (Exception err)
        {
            // If an error occurs, return a failure code
            System.out.println("Error: " + err);
            return 2;
        }
    }

    // This method is used to write to the CSV file and add a new user to the database
    public static int addUser(File db, String user, String pass, String name)
    {
        // Convert id to int
        int tempID = Integer.parseInt(id) + 1;
        id = Integer.toString(tempID);

        // Get the current date and store it in the format 'MMM-DD'
        java.util.Date date = new java.util.Date();
        String dateString = date.toString().substring(4, 10);

        try
        {
            // Initializing a buffered writer to write to the file
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(db, true));
            // Write the new user's information to the file
            csvWriter.append(user + "," + pass + "," + name + "," + id + "," + "Online" + "," + dateString + "\n");
            // Set the user's status to Online
            setStatus(db, user, "Online");
            csvWriter.close();
            // Add the new user to the database arraylist for use later
            usernames.add(user);
            // Print the new user's information to the console (to show success)
            System.out.println("Logging in user " + user + "\nName: " + name + "\nId: " + id);
            // Return a success code
            return 0;
        }
        catch (Exception err)
        {
            // If an error occurs, return a failure code
            System.out.println("Error: " + err);
            return 1;
        }
    }

    // This method is used to read the CSV file, set the latest id to the highest id in the database, and get all the users in the database
    public static void startup(File db)
    {
        // Clear the database arraylists (from previous sessions)
        currentDb.clear();
        usernames.clear();
        String row = "";
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader csvReader = new BufferedReader(new FileReader(db));
            // While the current line is not empty, read the file line by line
            while ((row = csvReader.readLine()) != null)
            {
                String [] data = row.split(",");
                // Set the current line's id to the current id
                id = data[3];
                // Add the current line's username to the usernames arraylist
                usernames.add(data[0]);
            }
            csvReader.close();
            System.out.println("Latest id: " + id);
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error reading id: " + err);
        }  
    }

    // This method is used to write to the CSV file and set the user's status
    public static void setStatus(File db, String user, String status)
    {
        String row = "";
        // Create a variable to remember which row the user is on (which row to be written to)
        int dbrow = 0;
        // Get date and store it in the format 'MMM-DD'
        java.util.Date date = new java.util.Date();
        String dateString = date.toString().substring(4, 10);
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader csvReader = new BufferedReader(new FileReader(db));
            // While the current line is not empty, read the file line by line
            while ((row = csvReader.readLine()) != null)
            {
                // Store the line's data in the variable array 'data'
                String [] data = row.split(",");
                // If the given user matches the current line's user...
                if (data[0].equals(user))
                {
                    // Set the users status in the array to the given status
                    data[4] = status;
                    // Store the data array in a string
                    String info = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + dateString;
                    // Write the new information to the file at the the row the user is on
                    writeToDB(db, info, dbrow);

                }
                // Otherwise increment the row the user is on 
                dbrow++;
            }
            csvReader.close();
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error: " + err);
        }
    }

    // This method is used to write to the CSV file and set data for a user
    public static void writeToDB(File db, String info, int dbrow)
    {
        // clear the current db (because the database is being rewritten/changed)
        currentDb.clear();
        String row = "";
        try
        {
            // Initializing a buffered reader to read the csv file
            BufferedReader csvReader = new BufferedReader(new FileReader(db));
            // While the current line is not empty, read the file line by line
            while ((row = csvReader.readLine()) != null)
            {
                // Store each row in an array called 'data'
                String [] data = row.split(",");
                // Using a temp list to store the current line's data
                List<String> temp = new ArrayList<String>();
                // Add each element of the data array to the temp list
                for (int i = 0; i < data.length; i++)
                {
                    temp.add(data[i]);
                }
                // Add the temp list to the current db array
                currentDb.add(temp);
            }
            csvReader.close();
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error: " + err);
        }

        // write to the file
        try
        {
            // Initializing a buffered writer to write to the file
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(db, false));
            // For each row in the current db array (the database)
            for (int i = 0; i <= Integer.parseInt(id); i++)
            {
                // If the current row is the row the user is on...
                if (i == dbrow)
                {
                    // Write the new information to the file
                    csvWriter.append(info + "\n");
                }
                // If the current row is is the first row (the header row)
                else if(i == 0)
                {
                    // Write the header row to the file (Username, Password, Name, Id, Status, Date)
                    csvWriter.append(currentDb.get(i).get(0) + "," + currentDb.get(i).get(1) + "," + currentDb.get(i).get(2) + "," + currentDb.get(i).get(3) + "," + currentDb.get(i).get(4) + "," + currentDb.get(i).get(5) + "\n");
                }
                // Otherwise, write the original data to the file
                else
                {
                    csvWriter.append(currentDb.get(i).get(0) + "," + currentDb.get(i).get(1) + "," + currentDb.get(i).get(2) + "," + currentDb.get(i).get(3) + "," + "Offline" + "," + currentDb.get(i).get(5) + "\n");
                }
            }
            csvWriter.close();
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error here: " + err);
        }
    }

    // This method is used edit a users data in the database
    public static void editUser(File db, String user, String pass, String name)
    {
        String row = "";
        int dbrow = 0;
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader csvReader = new BufferedReader(new FileReader(db));
            // While the current line is not empty, read the file line by line
            while ((row = csvReader.readLine()) != null)
            {
                String [] data = row.split(",");
                // If the given user matches the current line's user...
                if (data[0].equals(user))
                {
                    // Set the users password in the array to the given password
                    // Set the users name in the array to the given name
                    data[1] = pass;
                    data[2] = name;
                    // Store the data array in a string
                    String info = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                    // Write the new information to the file at the the row the user is on
                    writeToDB(db, info, dbrow);
                }
                // Otherwise increment the row the user is on
                dbrow++;
            }
            csvReader.close();
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error: " + err);
        }
    }

    // This method is used to get a users data from the database
    public static String [] getUserInfo(File db, String user)
    {
        String row = "";
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader csvReader = new BufferedReader(new FileReader(db));
            // While the current line is not empty, read the file line by line
            while ((row = csvReader.readLine()) != null)
            {
                String [] data = row.split(",");
                // If the given user matches the current line's user...
                if (data[0].equals(user))
                {
                    // Return the data array
                    return data;
                }
            }
            csvReader.close();
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error: " + err);
        }
        // If the user is not found, return nothing
        return null;
    }

    // This method is used to get a random post (line) from the posts.txt file
    public static String getRandomPost()
    {
        // set the number of lines in the file to 0
        lines = 0;
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader txtReader = new BufferedReader(new FileReader("assets/posts.txt"));
            String line = "";
            // While the current line is not empty, read the file line by line
            while ((line = txtReader.readLine()) != null)
            {
                // Increment the number of lines in the file
                lines++;
            }
            txtReader.close();
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error: " + err);
        }

        // get a random line from the file
        try
        {
            // Initializing a buffered reader to read the file
            BufferedReader txtReader = new BufferedReader(new FileReader("assets/posts.txt"));
            String line = "";
            // Call the genRandNum method to get a random number between 1 and the number of lines in the file
            genRandNum();
            while(true)
            {
                // Jump to the randomly picked line
                for (int i = 0; i < random; i++)
                {
                    line = txtReader.readLine();
                }
                // If the line is empty, generate a random number again and try again
                if(line == "" || line == null)
                {
                    genRandNum();
                }
                // Otherwise, break out of the loop and add the random number to the array of already chosen values
                else
                {
                    chosen.add(random);
                    break;
                }
            }
            txtReader.close();
            // Print the line to the console
            System.out.println("Random post: " + line);
            // Return the line
            return line;
        }
        catch (Exception err)
        {
            // If an error occurs, print the error to the console
            System.out.println("Error: " + err);
        }
        // If everything fails, return nothing
        return null;
    }

    // This method is used to get a random number between 1 and the number of lines in the posts.txt file
    public static void genRandNum()
    {
        while(true)
        {
            // Generate a random number between 1 and the number of lines in the file
            random = (int)(Math.random() * lines);
            // If the random number is equal to 0, restart the loop
            if(random == 0)
            {
                continue;
            }
            // If the random number is not already in the list of already chosen values, add it to the list and break out of the loop
            if(!chosen.contains(random))
            {
                chosen.add(random);
                break;
            }
        }
    }
}
