    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import java.io.*;
    import java.util.ArrayList;

    public class SocialGate extends JFrame 
    {
        // Instance Variables
        File database = new File("assets/database.csv");
        Image icon = Toolkit.getDefaultToolkit().getImage("assets/sgIcon512.png"); 

        String user;
        String pass;
        String name;

        String randUserStatus;
        String randUserLastOnline;
        String randUserName;

        boolean loggedIn = false;

        // make arraylist of ints
        ArrayList<Integer> chosen = new ArrayList<Integer>(10);
        String userData [] = new String[6];

        // Login Panels and Variables (Login)
        JPanel mainLogin = new JPanel();
        JPanel login = new JPanel();
        JLabel sglogo = new JLabel(new ImageIcon("assets/loginlogo.png"));
        JLabel username = new JLabel("Username:");
        JTextField usernameLabel = new JTextField("");
        JLabel password = new JLabel("Password:");
        JPasswordField passwordLabel = new JPasswordField();
        JButton loginbutton = new JButton("Login");
        JButton signupbutton = new JButton("Don't have an account? Sign Up!");
        JLabel [] loginLabels = {username, password};
        JButton [] loginButtons = {loginbutton};

        // Home screen panels and variables
        JPanel mainHome = new JPanel();
        JPanel navBar = new JPanel();
        JPanel viewportContent = new JPanel();
        JPanel homePanel = new JPanel();
        JScrollPane homeScroll = new JScrollPane();
        JLabel homeImg = new JLabel(new ImageIcon("assets/homeImg.png"));

        // Navbar buttons and variables
        JLabel sglogo2 = new JLabel(new ImageIcon("assets/sgIcon51.png"));
        JButton homeButton = new JButton("Home");
        JButton friendsButton = new JButton("Friends");
        JButton messagesButton = new JButton("Messages");
        JButton settingsButton = new JButton("Settings");
        JButton logoutButton = new JButton("Logout");
        
        JButton [] homeButtons = {homeButton, friendsButton, messagesButton, settingsButton, logoutButton};

        // Post container JPanel for holding home screen posts
        JPanel postContainer = new JPanel();
        // ArrayList for holding the posts
        ArrayList<JPanel> posts = new ArrayList<JPanel>();
        // ArrayList for holding the post data
        ArrayList<JTextArea> postData = new ArrayList<JTextArea>();

        // Loading screen panels and variables
        JPanel mainLoading = new JPanel();
        JPanel loadPanel = new JPanel();
        JLabel loading = new JLabel("Loading...");
        JLabel loadImgLabel = new JLabel();
        ImageIcon loadImg = new ImageIcon("assets/loading.gif");
        JLabel [] loadLabels = new JLabel[]{loading};

        // Signup screen panels and variables
        JPanel mainSignup = new JPanel();
        JPanel signup = new JPanel();
        JLabel signUpName = new JLabel("Name:");
        JTextField signUpNameLabel = new JTextField("");
        JLabel signUpUsername = new JLabel("Username:");
        JTextField signUpUsernameLabel = new JTextField("");
        JLabel signUpPassword = new JLabel("Password:");
        JPasswordField signUpPasswordLabel = new JPasswordField();
        JButton signUpButton = new JButton("Sign Me Up!");
        JButton signUpBack = new JButton("Back");
        JLabel [] signUpLabels = {signUpName, signUpUsername, signUpPassword};
        JButton [] signUpButtons = {signUpButton, signUpBack};

        // Messages Screen Panels and Variables
        JPanel mainMessages = new JPanel();
        JPanel messages = new JPanel();

        // Friends Screen Panels and Variables
        JPanel mainFriends = new JPanel();
        JPanel friendsPanel = new JPanel();
        JScrollPane friendsScroll = new JScrollPane();
        Icon searchIcon = new ImageIcon("assets/search.png");
        JButton search = new JButton(searchIcon);
        JPanel searchPanel = new JPanel();
        ArrayList<JPanel> friendprofiles = new ArrayList<JPanel>();
        JPanel friendsViewport = new JPanel();
        JLabel friendsusername = new JLabel("Username");

        JButton [] friendsButtons = {search};

        // Settings Screen Panels and Variables
        JPanel mainSettings = new JPanel();
        JPanel settings = new JPanel();
        JPanel settingsContent = new JPanel();
        JScrollPane settingsScroll = new JScrollPane();
        JButton editBG = new JButton("Edit Background");
        JButton editProfile = new JButton("Edit Profile");
        JButton credits = new JButton("Credits");
        JLabel spacers = new JLabel("----------------------------------------------------");

        JButton [] settingsButtons = {editBG, editProfile, credits};
        
        //Edit background Variables and Panels
        JPanel mainEditBGPanel = new JPanel();
        JPanel editBGPanel = new JPanel();
        JPanel editBGTitlePanel = new JPanel();
        JPanel editBGButtonsPanel = new JPanel();
        JLabel editBGTitle = new JLabel("Edit Background");
        JLabel editBGIcon = new JLabel(new ImageIcon("assets/editBGIcon.png"));
        Color bgRed = new Color(255,0,0);
        Color bgBlue = new Color(3,7,252);
        Color bgLime = new Color(3,252,44);
        Color bgHotPink = new Color(252,3,194);
        Color bgBabyBlue = new Color(3,252,244);
        Color regular = new Color(102, 102, 255);
        Color bgOrange = new Color(252,140,3);
        Color bgGatesPink = new Color(252,83,108);
        Color currentColor = regular;

        //Edit background color buttons
        JButton returnToSettings = new JButton("Return and Apply");
        JButton bgRedButton = new JButton("Red");
        JButton bgBlueButton = new JButton("Blue");
        JButton bgHotPinkButton = new JButton("Hot Pink");
        JButton bgRegularButton = new JButton("Regular");
        JButton bgOrangeButton = new JButton("Orange");
        JButton bgGatesPinkButton = new JButton("Gates Pink");

        JButton [] editBGButtons = {bgRedButton, bgBlueButton, bgHotPinkButton, bgRegularButton, bgOrangeButton, bgGatesPinkButton};
        Color [] editBGColors = {bgRed, bgBlue, bgHotPink, regular, bgOrange, bgGatesPink};
        JButton [] editBGButtons2 = {returnToSettings};

        // Edit profile Variables and Panels
        JPanel mainEditProfilePanel = new JPanel();
        JPanel editProfilePanel = new JPanel();
        JPanel editProfilePicPanel = new JPanel();
        JLabel editProfilePic = new JLabel(new ImageIcon("assets/blankPFP64.png"));
        JLabel editProfileTitle = new JLabel("Edit Profile");
        JButton namechange = new JButton("Change Name");
        JButton passwordchange = new JButton("Change Password");
        JButton changeusername = new JButton("Change Username");
        JLabel currentUsername = new JLabel("Current Username: ");
        JLabel currentName = new JLabel("Current Name: ");

        JButton [] editProfileButtons = {namechange, passwordchange, changeusername, returnToSettings};
        JLabel [] editProfileLabels = {currentUsername, currentName};

        // All buttons
        JButton [] allButtons = {homeButton, friendsButton, messagesButton, settingsButton, logoutButton, loginbutton, signupbutton, signUpButton, signUpBack, bgRedButton, bgBlueButton, bgHotPinkButton, bgRegularButton, bgOrangeButton, bgGatesPinkButton, returnToSettings, editBG, search, editProfile, namechange, passwordchange, changeusername, credits};

        // Main Process
        public static void main(String[] args) throws Exception 
        {
            new SocialGate();
        }

        // Method to add blank labels to any panel
        // Adds a completely empty JLabel to a specified panel (x), a specified amount of times (n)
        public void addBlanks(JPanel x, int n)
        {
            for (int i = 0; i < n; i++)
            {
                x.add(new JLabel(""));
            }
        }

        // Method to setup default labels on any screen
        // Sets up and array of labels to use a font
        public void labelSetup(JLabel [] x)
        {
            for (int i = 0; i < x.length; i++)
            {
                x[i].setFont(new Font("Bahnschrift", Font.PLAIN, 16));
            }
        }

        // Method to setup default buttons on any screen
        // Sets up an array of buttons with a default font, centered and with a white foreground and custom color coded background
        public void buttonSetup(JButton [] x)
        {
            for (int i = 0; i < x.length; i++)
            {
                x[i].setFont(new Font("Bahnschrift", Font.PLAIN, 20));
                x[i].setBackground(new Color(51, 102, 255));
                x[i].setForeground(Color.WHITE);
                x[i].setHorizontalAlignment(SwingConstants.CENTER);
            }
        }

        // Login Screen Code
        public void loginScreen()
        {
            // Begin program startup sequence
            sgdbCSV.startup(database);
            loggedIn = false;
            // Clear fields
            usernameLabel.setText("");
            passwordLabel.setText("");
            // Reset bg colour
            currentColor = regular;
            // mainLogin panel setup   
            mainLogin.setBackground(new Color(102, 102, 255));
            mainLogin.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70));
            mainLogin.setLayout(new GridLayout(1,1));

            // Call buttonSetup method to set up buttons
            buttonSetup(loginButtons);

            // setup signup button
            signupbutton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
            signupbutton.setBackground(new Color(51, 102, 255));
            signupbutton.setForeground(Color.WHITE);
            
            //Call labelSetup method to set up labels
            labelSetup(loginLabels);
        
            // Login Panel Setup
            login.setLayout(new GridLayout(7,1, 10, 10));
            // set login panel to be within boundaries of mainLogin border
            login.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            login.add(sglogo);
            // Center sglogo
            sglogo.setHorizontalAlignment(JLabel.CENTER);
            //Add login panel elements
            login.add(username);
            login.add(usernameLabel);
            login.add(password);
            login.add(passwordLabel);
            login.add(loginbutton);
            login.add(signupbutton);
            mainLogin.add(login);

            // initialize frame
            setIconImage(icon);
            setContentPane(mainLogin);
            setTitle("SocialGate - Login");
            setSize(500, 500);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter()
            //Function to make user confirm whether they want to quit the program
            {
                public void windowClosing(WindowEvent e)
                {
                    int result = JOptionPane.showConfirmDialog(null, "Leaving so soon?", "Exit", JOptionPane.YES_NO_OPTION);
                    // if the user clicks yes, exit the program and close the window
                    if (result == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                }
            });
        }

        // Code to re-create the Navagation Bar
        public void rePaintNav()
        {
            // Clear the navagation bar's elements
            navBar.removeAll();
            // navBar panel setup
            navBar.setBackground(new Color(58, 58, 148));
            navBar.setLayout(new GridLayout(1,7, 16, 0));
            navBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 40));
            // call buttonSetup method to set up buttons
            buttonSetup(homeButtons);
            // add sg logo to navBar
            navBar.add(sglogo2);
            // add home buttons to navBar
            for (int i = 0; i < homeButtons.length; i++)
            {
                navBar.add(homeButtons[i]);
            }
        }

        // Home Screen Code
        public void homeScreen() 
        {
            //Removing all elements so that the panels do not hold on to previous elements every time
            navBar.removeAll();
            viewportContent.removeAll();

            // mainHome panel setup
            mainHome.setBackground(currentColor);
            mainHome.setBorder(BorderFactory.createEmptyBorder(0, 70, 20, 70));
            mainHome.setLayout(new FlowLayout());

            // Call repaintNav method to re-create the navagation bar
            rePaintNav();

            // Home Panel Setup
            homePanel.setLayout(new GridLayout(1,1));
            homePanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
            homePanel.setOpaque(false);
            homePanel.add(homeScroll);

            homeButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));

            // Home Scroll Panel Setup (the panel that the user scrolls through)
            homeScroll.getVerticalScrollBar().setValue(0x0);
            homeScroll.setPreferredSize(new Dimension(780, 550));
            homeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            // Make it so the horizontal scroll bar is not visible
            homeScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            homeScroll.getVerticalScrollBar().setBackground(new Color(58, 58, 148));
            // Setting how much the scroll bar will scroll by
            homeScroll.getVerticalScrollBar().setUnitIncrement(14);
            homeScroll.setBackground(new Color(102, 102, 255));
            homeScroll.setOpaque(true);
            homeScroll.setVisible(true);
            // Setting which panel the scroll pane will scroll/show
            homeScroll.setViewportView(viewportContent);
           

            // Setup viewportContent (the actual panel that the user will interact with)
            viewportContent.setLayout(new GridLayout(10, 1, 0, 10));
            viewportContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Call the createPosts method to create the posts to be displayed on the viewportContent panel
            createPosts();

            // Add each post to the viewportContent panel
            for(int i = 0; i < posts.size(); i++)
            {
                viewportContent.add(posts.get(i));
            }

            // add navBar panel to mainHome panel
            mainHome.add(navBar);

            // add home panel to mainHome panel
            mainHome.add(homePanel);

            // add home screen components to mainHome panel
            setContentPane(mainHome);
            setTitle("SocialGate - Home");
            setSize(880,740);
            // If statement to determine if the user is logged in or not
            if(loggedIn == false)
            {
                // If the user has just logged in, make the window appear in the center of the screen
                setLocationRelativeTo(null);
                // Set loggedIn to true (so that the window will not be centered again)
                loggedIn = true;
            }
        }

        // Code to generate random posts from random users
        public void createPosts()
        {
            // Clear the chosen numbers from both lists in the main file and extra file
            sgdbCSV.chosen.clear();
            chosen.clear();

            // Clear the JPanels from the posts list and viewportContent panel
            posts.clear();
            viewportContent.removeAll();

            // For loop that creates 10 posts
            for(int i = 0; i < 10; i++)
            {
                // If the loop is on the second last iteration, make this panel say "Latest Post"
                if(i == 8)
                {
                    // Create a new JPanel
                    JPanel post = new JPanel();
                    // Set up the panel
                    post.setLayout(new GridLayout(1,1));
                    post.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    // Create a new JLabel
                    JLabel latest = new JLabel("--- LATEST POST ---");
                    // Set up the JLabel
                    latest.setFont(new Font("Bahnschrift", Font.BOLD, 30));
                    latest.setHorizontalAlignment(JLabel.CENTER);
                    latest.setForeground(new Color(58, 58, 148));
                    // Add the label to the panel, then the panel to the list
                    post.add(latest);
                    posts.add(post);
                }
                // If the loop is on the first iteration, make this panel show the home image
                else if(i == 0)
                {
                    JPanel post = new JPanel();
                    post.setLayout(new GridLayout(1,1));
                    post.add(homeImg);
                    posts.add(post);
                }
                // Otherwise, create a new post
                else
                {
                    // create post panel
                    JPanel post = new JPanel();
                    // create a panel to hold the names (user name and name)
                    JPanel names = new JPanel();
                    // Set up the panels
                    post.setLayout(new GridLayout(1,3));
                    post.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
                    post.setBackground(new Color(214, 214, 214));
                    names.setLayout(new GridLayout(2,1));
                    names.setBackground(new Color(214, 214, 214));

                    // Adding a blank profile picture to the post 
                    post.add(new JLabel(new ImageIcon("assets/blankPFP92.png")));

                    // Adding a user's name and username to the post
                    // We do this by calling the getRandName method to get a random user from the database
                    // We then set the returned user's name and username to the JLabels
                    JLabel randPostName = new JLabel(getRandName());
                    JLabel randPostUserName = new JLabel("@" + randUserName);

                    // Setting up the font for the name
                    randPostName.setFont(new Font("Bahnschrift", Font.BOLD, 20));
                    randPostName.setForeground(new Color(58, 58, 148));
                    randPostName.setVerticalAlignment(JLabel.BOTTOM);

                    // Setting up the font for the user name
                    randPostUserName.setFont(new Font("Bahnschrift", Font.BOLD, 15));
                    randPostUserName.setForeground(new Color(58, 58, 148));
                    randPostUserName.setVerticalAlignment(JLabel.TOP);

                    // Adding the name and user name to the names panel
                    names.add(randPostName);
                    names.add(randPostUserName);

                    // Creating a new JLabel to hold the post text
                    // We do this by calling the getRandomPost method to get a random line from the posts.txt file
                    // We then set the text to the JLabel
                    JTextArea randPostText = new JTextArea(sgdbCSV.getRandomPost());
                    // Setting up the post text
                    randPostText.setEditable(false);
                    // Making it so the words stay within the box (in the JPanel) and not go off the screen
                    randPostText.setLineWrap(true);
                    randPostText.setWrapStyleWord(true);
                    randPostText.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
                    randPostText.setBackground(new Color(214, 214, 214));
                    randPostText.setForeground(new Color(58, 58, 148));
                    // Adding everything to the post panel
                    post.add(names);
                    post.add(randPostText);
                    posts.add(post);
                }
            }
        }

        // Loading Screen Code
        public void loadToHome()
        {
            // Clearing all elements, labelSetup
            loadPanel.removeAll();
            labelSetup(loadLabels);
            // Resetting the main text label to say "Loading..."
            loadLabels[0].setText("Loading...");
            
            // Load Panel Setup
            mainLoading.setBackground(new Color(102, 102, 255));
            mainLoading.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70));
            mainLoading.setLayout(new GridLayout(1,1));
            loadPanel.setLayout(new GridLayout(4,1, 10, 5));
            // Adding a blank for spacing
            addBlanks(loadPanel, 1);

            // set loading panel to have same border as mainLoading
            loadPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

            // Label/Panel Setup and adding text + image to panel
            loadImgLabel.setIcon(loadImg);
            loadPanel.add(loading);
            loadPanel.add(loadImgLabel);
            
            // Center loading
            loading.setHorizontalAlignment(JLabel.CENTER);
            
            // Center loadimg
            loadImgLabel.setHorizontalAlignment(JLabel.CENTER);
            // Adding the loading panel to the main loading panel
            mainLoading.add(loadPanel);
            
            //Setting content pane
            setContentPane(mainLoading);
            setTitle("Loading...");
            setSize(500, 500);
            setResizable(false);
            setVisible(true);

            // Timers to pause the program and create a fake loading screen
            // Then go to home screen once done
            // Time is measured in milliseconds
            Timer secondLoad = new Timer(1000, new ActionListener()
            {
                // This timer is called second
                public void actionPerformed(ActionEvent e)
                {
                    // Go to the home screen
                    homeScreen();
                }
            });

            Timer firstLoad = new Timer(1000, new ActionListener()
            {
                // This timer is called first
                public void actionPerformed(ActionEvent e)
                {
                    // Change the label to welcome the user after a second
                    loading.setFont(new Font("Bahnschrift", Font.BOLD, 22));
                    loading.setText("Welcome" + " " + user + "!");
                    // Start the next timer
                    secondLoad.start();
                }
            });

            // Make sure the timers don't repeat! (Will cause problems if they do)
            firstLoad.setRepeats(false);
            secondLoad.setRepeats(false);
            firstLoad.start();
        }

        // Sign Up Screen Code
        public void signupScreen()
        {
            // clear signup labels
            signUpNameLabel.setText("");
            signUpUsernameLabel.setText("");
            signUpPasswordLabel.setText("");
            // mainSignup panel setup
            mainSignup.setBackground(new Color(102, 102, 255));
            mainSignup.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70));
            mainSignup.setLayout(new GridLayout(1,1));
            buttonSetup(signUpButtons);
            labelSetup(signUpLabels);

            // Signup Panel Setup
            signup.setLayout(new GridLayout(9,1, 10, 10));
            // set signup panel to be within boundaries of mainSignup border
            signup.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            signup.add(sglogo);
            // Center sglogo
            sglogo.setHorizontalAlignment(JLabel.CENTER);
            // Adding elements to signup panel in the right order
            signup.add(signUpName);
            signup.add(signUpNameLabel);
            signup.add(signUpUsername);
            signup.add(signUpUsernameLabel);
            signup.add(signUpPassword);
            signup.add(signUpPasswordLabel);
            signup.add(signUpButton);
            signup.add(signUpBack);

            // Adding the signup panel to the main signup panel
            mainSignup.add(signup);

            // initialize frame
            setContentPane(mainSignup);
            setTitle("SocialGate - Signup");
            setSize(500, 570);
            setResizable(false);
            setVisible(true);
        }

        // Friends Screen Code
        public void friendsScreen()
        {
            // Removing navbar elements because it will be repainted after
            navBar.removeAll();

            // mainHome panel setup
            mainFriends.setBackground(currentColor);
            mainFriends.setBorder(BorderFactory.createEmptyBorder(0, 70, 20, 70));
            mainFriends.setLayout(new FlowLayout());

            // Repainting the navbar
            rePaintNav();

            // Adding friendsPanel elements and navbar
            mainFriends.add(navBar);
            mainFriends.add(friendsPanel);
            // Highlighting the friends button
            friendsButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
            // Setting up friendsPanel
            friendsPanel.setLayout(new GridLayout(1,1));
            friendsPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
            friendsPanel.setOpaque(false);
            friendsPanel.add(friendsScroll);

            // Reset friendScrollbar to top
            friendsScroll.getVerticalScrollBar().setValue(0);

            // Friends Scroll Pane Setup
            friendsScroll.setPreferredSize(new Dimension(780, 550));
            friendsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            friendsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            friendsScroll.getVerticalScrollBar().setBackground(new Color(58, 58, 148));
            friendsScroll.getVerticalScrollBar().setUnitIncrement(12);
            friendsScroll.setBackground(new Color(102, 102, 255));
            friendsScroll.setOpaque(true);
            friendsScroll.setVisible(true);
            friendsScroll.setViewportView(friendsViewport);

            // Setup friendsViewport
            // Executing createprofiles method and using a for loop to add the new profiles to the scrollpanel viewport
            createProfiles();
            friendsViewport.setLayout(new GridLayout(12,1, 0, 10));
            friendsViewport.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            friendsViewport.add(new JLabel(new ImageIcon("assets/friendsmsg.png")));
            friendsViewport.add(searchPanel);
            searchPanel.setLayout(new GridLayout(1,1));
            searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            searchPanel.add(search);
            buttonSetup(friendsButtons);
            for(int i = 0; i < friendprofiles.size(); i++)
            {
                friendsViewport.add(friendprofiles.get(i));
            }

            // Initialize frame
            setContentPane(mainFriends);
            setTitle("SocialGate - Friends");
            setSize(880,740);
            setVisible(true);
            setResizable(false);
        }
        
        // Code to generate suggested profiles (that are simply random profiles)
        public void createProfiles()
        {
            // Clearing lists and panels to prevent duplicates
            chosen.clear();
            friendsViewport.removeAll();
            friendprofiles.clear();

            // For loop that creates a random profile for each iteration (10 times)
            for(int i = 0; i < 10; i++)
            {
                // create profile panels and defining their format
                JPanel profile = new JPanel();
                JPanel names = new JPanel();
                names.setLayout(new GridLayout(2,1));
                names.setBackground(new Color(214, 214, 214));
                profile.setLayout(new GridLayout(1,4));
                profile.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
                profile.setBackground(new Color(214, 214, 214));
                profile.setOpaque(true);
                profile.setPreferredSize(new Dimension(200, 200));
                profile.setVisible(true);
                // create profile labels for displaying stats such as the username, real name, date last online and the current status
                // Formatting the profile labels to all have a uniform look, using the same font, same color, and the same alignment
                JLabel profileName = new JLabel(getRandName());
                JLabel profileUserName = new JLabel("@" + randUserName);
                JLabel currentStatus = new JLabel("Status: " + randUserStatus);
                JLabel lastOnline = new JLabel("Last Online: " + randUserLastOnline);
                lastOnline.setFont(new Font("Bahnschrift", Font.BOLD, 20));
                lastOnline.setForeground(new Color(58, 58, 148));
                lastOnline.setHorizontalAlignment(JLabel.CENTER);
                currentStatus.setFont(new Font("Bahnschrift", Font.BOLD, 20));
                currentStatus.setForeground(new Color(58, 58, 148));
                currentStatus.setHorizontalAlignment(JLabel.CENTER);
                profileName.setFont(new Font("Bahnschrift", Font.BOLD, 20));
                profileName.setForeground(new Color(58, 58, 148));
                profileName.setHorizontalAlignment(JLabel.CENTER);
                profileName.setVerticalAlignment(JLabel.BOTTOM);
                profileUserName.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
                profileUserName.setForeground(new Color(58, 58, 148));
                profileUserName.setHorizontalAlignment(JLabel.CENTER);
                profileUserName.setVerticalAlignment(JLabel.TOP);
                //Adding a blank profile picture to the profile card
                profile.add(new JLabel(new ImageIcon("assets/blankPFP.png")));
                //Adding all elements to the profile card/panel
                names.add(profileName);
                names.add(profileUserName);
                profile.add(names);
                profile.add(currentStatus);
                profile.add(lastOnline);
                // add profile panel to arraylist
                friendprofiles.add(profile);
            }
        }

        // Code to grab random users from database
        public String getRandName()
        {
            int rand;
            while(true)
            {
                // Generate a random number between 1 and the number of users in the database
                rand = (int)(Math.random() * sgdbCSV.usernames.size());
                // If the random number is equal to 0, continue to generate a new random number
                if(rand == 0)
                {
                    continue;
                }
                // If the random number is equal to the current user's id, continue to generate a new number
                if(rand == Integer.parseInt(userData[3]))
                {
                    continue;
                }
                // check if rand is in the chosen ArrayList, if not, add it and break out of the loop
                // If it is, continue to generate a new number
                if(!chosen.contains(rand))
                {
                    chosen.add(rand);
                    break;
                }
            }
            // Grab a username from the database using the id (rand) and get the respective data (username, real name, last online, and status)
            String tempusername = sgdbCSV.usernames.get(rand);
            String randUserData [] = sgdbCSV.getUserInfo(database, tempusername);
            // Set the variables to the respective data for the profile cards
            randUserLastOnline = randUserData[5];
            randUserStatus = randUserData[4];
            randUserName = randUserData[0];
            // Return the real name of the user
            return randUserData[2];
        }

        // Settings Screen Code
        public void settingsScreen()
        {
            // Clearing panel elements
            navBar.removeAll();
            settingsContent.removeAll();
            mainSettings.removeAll();

            // mainHome panel setup
            mainSettings.setBackground(currentColor);
            mainSettings.setBorder(BorderFactory.createEmptyBorder(0, 70, 20, 70));
            mainSettings.setLayout(new FlowLayout());

            // navBar repaint
            rePaintNav();
            // Highlighting the settings button
            settingsButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));

            // settings panel setup
            settings.setLayout(new GridLayout(1,1, 0, 30));
            settings.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            settings.add(settingsContent);

            // settingsContent panel setup
            settingsContent.setLayout(new GridLayout(4,1, 0, 20));
            settingsContent.add(new JLabel(new ImageIcon("assets/settingsImg.png")));

            // Settings buttons setup (and adding them to the settingsContent panel)
            for(int i = 0; i < settingsButtons.length; i++)
            {
                settingsButtons[i].setFont(new Font("Bahnschrift", Font.PLAIN, 26));
                settingsButtons[i].setBackground(new Color(51, 102, 255));
                settingsButtons[i].setForeground(new Color(255, 255, 255));
                settingsContent.add(settingsButtons[i]);
            }
            // Adding the navbar to the mainSettings panel
            mainSettings.add(navBar);
            // Adding a spacers label to the mainSettings panel to make it look better (the navbar is too close to the top of the panel)
            mainSettings.add(spacers);
            // Making the spacers label invisible
            spacers.setForeground(currentColor);
            spacers.setFont(new Font("Bahnschrift", Font.BOLD, 20));
            // Addding the settings panel to the mainSettings panel
            mainSettings.add(settings);

            //Setting up window/frame
            setContentPane(mainSettings);
            setTitle("SocialGate - Settings");
            setSize(880,740);
            setVisible(true);
            setResizable(false);
        }

        // Edit Background Screen Code
        public void editBG()
        {
            // Clearing panel elements
            editBGPanel.removeAll();
            editBGPanel.removeAll();
            editBGTitlePanel.removeAll();
            editBGButtonsPanel.removeAll();

            // mainEditBGPanel setup
            mainEditBGPanel.setBackground(currentColor);
            mainEditBGPanel.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70));
            mainEditBGPanel.setLayout(new GridLayout(1,1));

            // editBGPanel setup
            editBGPanel.setLayout(new GridLayout(2,1,10, 10));
            editBGPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            editBGPanel.add(editBGTitlePanel);
            
            // editBGTitlePanel setup
            editBGTitlePanel.setLayout(new GridLayout(1,2));
            editBGTitlePanel.add(editBGTitle);
            editBGTitlePanel.add(editBGIcon);
            editBGIcon.setHorizontalAlignment(JLabel.RIGHT);
            
            // editBGTitle label setup/formatting
            editBGTitle.setFont(new Font("Bahnschrift", Font.BOLD, 36));
            editBGTitle.setForeground(new Color(58, 58, 148));
            editBGTitle.setHorizontalAlignment(JLabel.LEFT);
            editBGTitle.setVerticalAlignment(JLabel.CENTER);

            // Adding the editBGButtonsPanel to the editBGPanel
            editBGPanel.add(editBGButtonsPanel);
            editBGButtonsPanel.setLayout(new GridLayout(3,3, 10, 10));

            // editBGButtons setup
            for(int i = 0; i < editBGButtons.length; i++)
            {
                editBGButtons[i].setBackground(editBGColors[i]);
                editBGButtons[i].setForeground(Color.WHITE);
                editBGButtons[i].setFont(new Font("Bahnschrift", Font.PLAIN, 20));
                editBGButtonsPanel.add(editBGButtons[i]);
            }

            // Setting up the return button
            buttonSetup(editBGButtons2);

            // Adding blanks to the editBGButtonsPanel for spacing
            addBlanks(editBGButtonsPanel, 1);
            // Adding the return button to the editBGButtonsPanel
            editBGButtonsPanel.add(returnToSettings);
            returnToSettings.setText("Return and Apply");;

            // Adding the editBGPanel to the mainEditBGPanel
            mainEditBGPanel.add(editBGPanel);

            // setting up the window/frame
            setContentPane(mainEditBGPanel);
            setTitle("SocialGate - Edit Background");
            setSize(880,740);
            setVisible(true);
            setResizable(false);

        }

        // Edit Profile Screen Code
        public void editProfile()
        {
            //Setup mainEditProfilePanel
            mainEditProfilePanel.setBackground(currentColor);
            mainEditProfilePanel.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70));
            mainEditProfilePanel.setLayout(new GridLayout(1,1));

            //Clearing editProfile subpanel
            editProfilePanel.removeAll();

            //Setup editProfilePanel
            editProfilePanel.setLayout(new GridLayout(7,1, 10, 10));
            editProfilePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            editProfilePanel.add(editProfilePicPanel);
            editProfilePicPanel.setLayout(new GridLayout(1,2, 10, 10));
            editProfilePicPanel.add(editProfileTitle);
            editProfileTitle.setFont(new Font("Bahnschrift", Font.BOLD, 30));
            editProfileTitle.setForeground(new Color(58, 58, 148));
            editProfileTitle.setHorizontalAlignment(JLabel.LEFT);
            editProfilePicPanel.add(editProfilePic);
            editProfilePic.setHorizontalAlignment(JLabel.RIGHT);

            // Making labels display the users current username and name
            currentUsername.setText("Current Username: " + userData[0]);
            editProfilePanel.add(currentUsername);
            currentName.setText("Current Name: " + userData[2]);
            // For loop to format the two labels, setting the "Current Username" label to the bottom of it's label container and 
            // setting the "Current Name" label to the top of it's label container, creating a very small gap between them instead 
            // a large one
            for(int i = 0; i  < editProfileLabels.length; i++)
            {
                editProfileLabels[i].setFont(new Font("Bahnschrift", Font.BOLD, 22));
                editProfileLabels[i].setForeground(new Color(58, 58, 148));
                if(i == 0)
                {
                    editProfileLabels[i].setVerticalAlignment(JLabel.BOTTOM);
                }
                else
                {
                    editProfileLabels[i].setVerticalAlignment(JLabel.TOP);
                }
            }
            // Adding elements to edit Profile Panel and setting up the editProfileButtons
            buttonSetup(editProfileButtons);
            editProfilePanel.add(currentName);
            editProfilePanel.add(namechange);
            editProfilePanel.add(changeusername);
            editProfilePanel.add(passwordchange);
            editProfilePanel.add(returnToSettings);
            returnToSettings.setText("Return");

            // Adding subpanel to main panel
            mainEditProfilePanel.add(editProfilePanel);

            // Setting content pane
            setContentPane(mainEditProfilePanel);
            setTitle("SocialGate - Edit Profile");
            setSize(880,740);
            setVisible(true);
            setResizable(false);
        }

        // Code to handle name changes
        public void namechange()
        {
        // Creating a textfield and a password field and combining them into one object
            JFormattedTextField newName = new JFormattedTextField();
            JPasswordField enteredPass = new JPasswordField();
            Object[] message = {
                "New Name:", newName,
                "Password:", enteredPass
            };
        // Displaying a JOptionPane to ask the user to change their account name
        // If user does, Then allow user to enter their new name
        // and ask for their current password to confirm the change
            int option = JOptionPane.showConfirmDialog(null, message, "Change your Account Name", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) 
            {
                // If the password is correct, then change the name via the editUser method
                if(enteredPass.getText().equals(pass))
                {
                    name = newName.getText();
                    sgdbCSV.editUser(database, user, pass, name);
                    userData = sgdbCSV.getUserInfo(database, user);
                    currentName.setText("Current Name: " + userData[2]);
                }
                // If the password is incorrect, then display an error message
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        // Code to handle password changes
        public void passwordchange()
        {
        // Two password fields since it is a password and therefore should not be displayed to the user
        // Combining into one object for the JOptionPane
            JPasswordField newPassword = new JPasswordField();
            JPasswordField enteredPass = new JPasswordField();
            Object[] message = {
                "Current Password:", newPassword,
                "New Password:", enteredPass
            };
            // Ask the user for their current password and their new password
            int option = JOptionPane.showConfirmDialog(null, message, "Change your Password", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) 
            {
                // If the current password is correct, then change the password via the editUser method
                if(enteredPass.getText().equals(pass))
                {
                    pass = newPassword.getText();
                    sgdbCSV.editUser(database, user, pass, name);
                    userData = sgdbCSV.getUserInfo(database, user);
                }
                // If the current password is incorrect, then display an error message
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                }
            }
        
        }

        // Code to handle username changes
        public void changeusername()
        {
        // Creating a textfield and a password field and combining to one object
            JFormattedTextField newUsername = new JFormattedTextField();
            JPasswordField enteredPass = new JPasswordField();
            Object[] message = {
                "New Username:", newUsername,
                "Password:", enteredPass
            };
            // Allow user to enter their new username and their current password to confirm the change
            int option = JOptionPane.showConfirmDialog(null, message, "Change your Account Username", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) 
            {
                // If the username is too long, then display an error message
                if(newUsername.getText().length() > 11)
                {
                    JOptionPane.showMessageDialog(null, "Username must be 11 characters or less.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // If the password is correct...
                else if(enteredPass.getText().equals(pass))
                {
                    int doesUserExist = sgdbCSV.userExistCheck(database, newUsername.getText());
                    // ...and the username is not taken, then change the username via the editUser method
                    if(doesUserExist == 0)
                    {
                        user = newUsername.getText();
                        sgdbCSV.editUser(database, user, pass, name);
                        userData = sgdbCSV.getUserInfo(database, user);
                        currentUsername.setText("Current Username: " + userData[0]);
                    }
                    // ...and the username is taken, then display an error message
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // If the password is incorrect, then display an error message
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        
        }

        // Code to handle user searches
        public void searchUser()
        {
            // Creating an object with a textfield for search term for use in the JOptionPane
            JFormattedTextField searchTerm = new JFormattedTextField();
            Object[] message = {
                "Enter Username:", searchTerm
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Search Users", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) 
            {
                // Get the search term from the textfield
                String search = searchTerm.getText();
                // If the search term is the current user, then display an error message
                if(search.equals(user))
                {
                    JOptionPane.showMessageDialog(null, "You cannot search for yourself!");
                }
                // If the search term is not the current user, then search for the user using the getUserInfo method
                else
                {
                    String[] searchData = sgdbCSV.getUserInfo(database, search);
                    // If the user is not found (nothing in the string array), then display an error message
                    if(searchData == null)
                    {
                        JOptionPane.showMessageDialog(null, "User not found. Check your spelling and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // If the user is found, then display the user's information
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Username: " + searchData[0] + "\nName: " + searchData[2] + "\nStatus: " + searchData[4] + "\nLast Online: " + searchData[5], "User Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        // Message Screen Code
        public void messagesScreen()
        {
            // Clearing the messages panel
            messages.removeAll();
            
            // mainMessages panel setup
            mainMessages.setBackground(currentColor);
            mainMessages.setBorder(BorderFactory.createEmptyBorder(0, 70, 20, 70));
            mainMessages.setLayout(new FlowLayout());

            // Repainting the navbar
            rePaintNav();
            
            // highlighting the messages button
            messagesButton.setFont(new Font("Bahnschrift", Font.BOLD, 19));

            // setting up messages panel
            messages.setLayout(new FlowLayout());
            
            // make the messages panel fill the bottom portion of the frame
            messages.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            messages.add(new JLabel(new ImageIcon("assets/messagesError.png")));

            // adding the messages and navbar panels to the main messages panel
            mainMessages.add(navBar);
            mainMessages.add(messages);
            
            // Setting content pane
            setContentPane(mainMessages);
            setTitle("SocialGate - Messages");
            setSize(880,740);
            setVisible(true);
            setResizable(false);
            
        }
        
        // Constructor for the program (main method for when it is run)
        public SocialGate()
        {
            // add listeners to all buttons
            for (int i = 0; i < allButtons.length; i++)
            {
                allButtons[i].addActionListener(new ButtonListener());
            }
            loginScreen();
        }

        // Button Listener Class to handle all button clicks
        public class ButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == loginbutton)
                {
        // Setting user input to lowercase so that the username is not case sensitive
                    user = usernameLabel.getText().toLowerCase();
                    pass = passwordLabel.getText();

                    // if the username or password fields are empty, display an error message
                    if(user.equals("") || pass.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Please enter both a username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // If the username and password fields are not empty, then attempt to login
                    else
                    {
                        // Call the login method to attempt to login
                        int loginCode = sgdbCSV.loginCheck(database, user, pass);
                        if(loginCode == 0)
                        {
                    //  Loads the user's information from the database
                    //  and loads to home screen
                            userData = sgdbCSV.getUserInfo(database, user);
                            loadToHome();
                        }
                        else if(loginCode == 1)
                        {
                    // if the password is incorrect, display an error message
                            JOptionPane.showMessageDialog(null, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(loginCode == 2)
                        {
                    // if the username is incorrect, display an error message
                            JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                    // if there is an error, display an error message
                            JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    
                    
                }
                // Button on main login page
                else if (e.getSource() == signupbutton)
                {
                    signupScreen();
                }
                // button on sign up page (that has function)
                else if(e.getSource() == signUpButton)
                {
                    String tempUser = signUpUsernameLabel.getText().toLowerCase();
                    String tempPass = signUpPasswordLabel.getText();
                    String tempName = signUpNameLabel.getText();

                    // if the username or password fields are empty, display an error message
                    if(tempUser.equals("") || tempPass.equals("") || tempName.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields to continue.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // IF the length of the username is more than 11 characters, display an error message
                    else if(tempUser.length() > 11)
                    {
                        JOptionPane.showMessageDialog(null, "Username must be 11 characters or less.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // If the name is more than 18 characters, display an error message
                    else if(tempName.length() > 18)
                    {
                        JOptionPane.showMessageDialog(null, "Name must be 18 characters or less.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        // Check if the user exists in the database
                        int exist = sgdbCSV.userExistCheck(database, tempUser);
                        // If the user does not exist, then create the user
                        if(exist == 0)
                        {
                            int signUpCode = sgdbCSV.addUser(database, tempUser, tempPass, tempName);
                            // If the user was successfully created, then load to home screen and set the user data
                            if(signUpCode == 0)
                            {
                                JOptionPane.showMessageDialog(null, "Account created successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                user = tempUser;
                                pass = tempPass;
                                name = tempName;
                                userData = sgdbCSV.getUserInfo(database, user);
                                loadToHome();
                            }
                            // If the user was not created, then display an error message
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        // If the user already exists, then display an error message
                        }
                        else if(exist == 1)
                        {
                            JOptionPane.showMessageDialog(null, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        // If there is an error, then display an error message
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else if(e.getSource() == signUpBack)
                {
                    loginScreen();
                }
                else if(e.getSource() == logoutButton)
                {
                    // display a prompt to confirm logout
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION)
                    {
                        loginScreen();
                    }
                }

                // ALL IF STATEMENTS TO CHECK FOR NAVBAR CLICKS

                else if(e.getSource() == homeButton)
                {
                    homeScreen();
                }
                else if(e.getSource() == settingsButton)
                {
                    settingsScreen();
                }
                else if(e.getSource() == friendsButton)
                {
                    friendsScreen();
                }
                else if(e.getSource() == messagesButton)
                {
                    messagesScreen();
                }
                else if(e.getSource() == editBG)
                {
                    editBG();
                }

                // ALL IF STATEMENTS TO CHECK FOR BACKGROUND COLOR BUTTON CLICKS

                else if(e.getSource() == bgRedButton)
                {
                    currentColor = bgRed;
                    editBG();
                }
                else if(e.getSource() == bgBlueButton)
                {
                    currentColor = bgBlue;
                    editBG();
                }
                else if(e.getSource() == bgHotPinkButton)
                {
                    currentColor = bgHotPink;
                    editBG();
                }
                else if(e.getSource() == bgRegularButton)
                {
                    currentColor = regular;
                    editBG();
                }
                else if(e.getSource() == bgOrangeButton)
                {
                    currentColor = bgOrange;
                    editBG();
                }
                else if(e.getSource() == bgGatesPinkButton)
                {
                    currentColor = bgGatesPink;
                    editBG();
                }

                // ALL IF STATEMENTS TO CHECK FOR SETTINGS BUTTON CLICKS
                
                else if(e.getSource() == returnToSettings)
                {
                    settingsScreen();
                }
                else if(e.getSource() == search)
                {
                    searchUser();
                }
                else if(e.getSource() == editProfile)
                {
                    editProfile();
                }
                else if(e.getSource() == namechange)
                {
                    namechange();
                }
                else if(e.getSource() == changeusername)
                {
                    changeusername();
                }
                else if(e.getSource() == passwordchange)
                {
                    passwordchange();
                }
                else if(e.getSource() == credits)
                {
                    JOptionPane.showMessageDialog(null, "Program completed: June 2022\nAuthors: Kalan (Kay) Roye and Ruslan Saulin\nThanks for the amazing class, Mr. Piazza!", "Credits", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
