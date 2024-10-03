package ebank;



import java.time.LocalDateTime;
import java.util.*;

public class Bank {
    private ArrayList<Account> userAccounts = new ArrayList<>();
    private ArrayList<NewEmploye> employees = new ArrayList<>();
    private ArrayList<Manager> managers = new ArrayList<>();
    private ArrayList<Register> registers = new ArrayList<>();
    private ArrayList<Profile> profiles = new ArrayList<>();
    private ArrayList<Banks> banks = new ArrayList<>();
    private ArrayList<Bankowner> owners = new ArrayList<>();


    private Scanner sc = new Scanner(System.in); // Single Scanner instance
    int specialbankno = 0;
    public String Createnewbank() {
        // creating newbank
        System.out.println("Enter name of bank : ");
        String nm = sc.nextLine();
        System.out.println("Currancy of bank : ");
        String cur = sc.nextLine();


        // now we have to add a manue which shows all the bank no wise
        // and when user enter no thet that no of bank is going to open

        System.out.println("you seted the bank name and currancy now add bankowner detail -> ");

        // adding bank owner deatils
        System.out.println("'Add Bankowner details'->");
        System.out.println(" ");
        System.out.println("I. Enter bank owner name :");
        String nam = sc.nextLine();
        System.out.println("II. Enter age of bank owner :");
        int Age = errorhandle();
        System.out.println("III. Enter Phoneno of bank owner to varify :");
        long phoneno = errorhandle2();
        System.out.println("Iv. Add fund to the bank :");
        int fund = errorhandle();
        specialbankno++;
        String manager = null;

        Bankowner owner = new Bankowner(nam, Age, phoneno, fund,specialbankno);
        Banks banks1 = new Banks(nm, cur, fund, nam ,specialbankno,manager);


        banks.add(banks1);
        owners.add(owner);

        System.out.println("You all about seted just one more thing to add : ");
        System.out.println("That is manager of bank you can add him by main manue ");

        System.out.println("BUT FOR NOW TAKE A LOOK AT YOUR BANK DEAR " + nam);
        // displaying bank;
        bankststus(banks1.nameofbank ,owner.name);
        return nm;
    }

//    public void deletebank() {
//        System.out.println("enter bank name :");
//        String bname = sc.nextLine();
//        for (Banks banks1 : banks) {
//            if (Objects.equals(bname, banks1.nameofbank)) {
//                banks.remove(banks1);
//            } else {
//                System.out.println("bank not found!!");
//            }
//        }
//    }
//
//


    private void deletebank() {
        System.out.println("enter bank name :");
        String bname = sc.nextLine();
        boolean bankFound = false;
        for (int i = 0; i <banks.size(); i++) {  // Iterate using index
           Banks bank = banks.get(i);
            if (Objects.equals(bname,bank.nameofbank)) {
               banks.remove(i);  // Safe removal by index in ArrayList
                bankFound = true;
                System.out.println("Bank removed successfully.");
                break;  // Exit loop after removal
            }
        }

        if (!bankFound) {
            System.out.println("Manager ID not found.");
        }
    }

    int sno = 0;
    private void openexistingbank() {



         sno = errorhandle();
        if(banks.isEmpty()){
            System.out.println("No bank available");
        }
        else{
            System.out.println("To open a bank select the bank by Serial No.");
            for (int i = 0; i <banks.size(); i++) {  // Iterate using index
                Banks bank = banks.get(i);
                int m = i+1;
                System.out.println(m + "." + "Bank : " + bank.nameofbank);
            }
        }


        for (Banks banks1 : banks) {

            if (Objects.equals(sno, banks1.specialbno)) {

                   for(Bankowner bon : owners){

                       if (Objects.equals(banks1.specialbno,bon.specialno)){
                           bankststus(banks1.nameofbank,bon.name);
                       }
                   }



            }else {
                System.out.println("bank not found!");
            }
        }

    }

//    private void toprintnmaeofoner() {
//
//
//
//
//
//        for (Bankowner owner : owners) {
//            if (Objects.equals(sno, owner.specialno)) {
//
//                bankststus(owner.name);
//
//            }else {
//                System.out.println("bank not found!");
//            }
//        }
//    }
//

    public void crateManger(String nameofbank) {
        for (Banks bank1 : banks) {
            // Find the bank by name
            if (Objects.equals(nameofbank, bank1.nameofbank)) {
                // Add the manager and get the manager's name
                String manager = addmanager();

                // Set the manager's name to the current bank
                bank1.manager = manager;
                System.out.println("Manager assigned to the bank: " + bank1.nameofbank);
            }
        }
    }


    private String addmanager() {
        System.out.println("Enter manager Name:");

        String name = sc.nextLine();
        System.out.println("Enter manager Degree ");
        String degree = sc.nextLine();

        String manno = "MAN" + (int)Math.round(Math.random()*1000);
        Manager man = new Manager(name, degree, manno);
        managers.add(man);
        System.out.println("Manager Added ");
        System.out.println("manager id -> " + manno);
        return name;
    }


//    private void Removeman() {
//        System.out.println("enter Id of manager :");
//        String ID = sc.nextLine();
//        for (Manager man : managers) {
//            if (Objects.equals(ID, man.mno)) {
//                // NOTE ---> WE CAN'T USE DIRECT remove METHODE TO THE ARRALIST WHILE ITERATING OVER IT
//                //           USE Itarator to itarate over the Arraylist and remove an elsement
//
//              //  managers.remove(man);
//            } else {
//                System.out.println("manager id is not found");
//            }
//
//        }
//    }


    // removing by using Arrayist ----->

    private void Removeman(String nameofbank) {
        System.out.println("Enter ID of manager:");
        String ID = sc.nextLine();

        boolean managerFound = false;
        for (int i = 0; i < managers.size(); i++) {  // Iterate using index
            Manager man = managers.get(i);
            if (Objects.equals(ID, man.mno)) {
                managers.remove(i);  // Safe removal by index in ArrayList
                for (Banks bank1 : banks) {

                    if (Objects.equals(nameofbank, bank1.nameofbank)) {

                        bank1.manager = null;

                    }
                }
                managerFound = true;
                System.out.println("Manager removed successfully.");
                break;  // Exit loop after removal
            }
        }

        if (!managerFound) {
            System.out.println("Manager ID not found.");
        }
    }

    // removing by useing Itarator ---->

//    private void Removeman() {
//        System.out.println("Enter ID of manager:");
//        String ID = sc.nextLine();
//
//        Iterator<Manager> iterator = managers.iterator();
//        boolean managerFound = false;
//
//        while (iterator.hasNext()) {
//            Manager man = iterator.next();
//            if (Objects.equals(ID, man.mno)) {
//                iterator.remove();  // Use Iterator's remove method
//                managerFound = true;
//                System.out.println("Manager removed successfully.");
//                break;  // Break after removing the manager to avoid further iteration
//            }
//        }
//
//        if (!managerFound) {
//            System.out.println("Manager ID not found.");
//        }
//    }


    public Manager managerinfo(String manno) {
        for (Manager man : managers) {
            if (man.mno.equals(manno)) {
                System.out.println("man info ->");
                man.getmaninfo();
            }
            else{
                System.out.println("registered no is not match :");
            }
        }
        return null;
    }


    // Method to create a new user account
    public void createUserAccount() {
        System.out.println("Enter your name:");
        String name = sc.nextLine();
        System.out.println("Enter your age:");
        int age = errorhandle();
        System.out.println("Enter initial balance:");
        int balance = errorhandle();
        int accno = (int) (Math.random()*1000000);
        Account newAccount = new Account(name, age, accno, balance);
        userAccounts.add(newAccount);
        System.out.println("Account created successfully. Your Account No: " + newAccount.getAccNo());
    }


    // Method to create a new employee
    public void createEmployee() {
        System.out.println("Enter your name:");
        String name = sc.nextLine();
        System.out.println("Enter your email:");
        String email = sc.nextLine();
        System.out.println("Enter your password:");
        String password = sc.nextLine();


        int salary = 1000;
        System.out.println("Enter associated Account No:");
        int accNo = errorhandle();
        Account account = findAccountByNumber(accNo);
        if (account != null) {
            String regNo = "EMP" + (int) (Math.random()*1000);
            NewEmploye employee = new NewEmploye(name, email, password, regNo, salary, account);
            employees.add(employee);
            System.out.println("Employee registered successfully. Your Registration ID: " + regNo);
        } else {
            System.out.println("Account not found.");
        }
    }


    // Method to handle input mismatch errors
    public int errorhandle() {
        int input = 0;
        while (true) {
            try {
                input = sc.nextInt();
                sc.nextLine(); // Consume newline
                break; // Exit loop once valid input is provided
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a valid integer number:");
                sc.nextLine(); // Consume the invalid input
            }
        }
        return input; // Return the valid integer value
    }

    public int errorhandle2() {
        long input = 0;
        while (true) {
            try {
                input = sc.nextLong();
                sc.nextLine(); // Consume newline
                break; // Exit loop once valid input is provided
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a valid long type no number:");
                sc.nextLine(); // Consume the invalid input
            }
        }
        return (int) input; // Return the valid integer value
    }


    // Find an account by account number
    public Account findAccountByNumber(int accNo) {
        for (Account acc : userAccounts) {
            if (acc.getAccNo() == accNo) {
                return acc;
            } else {
                System.out.println("acc no is wrong");
            }
        }
        return null;
    }

    public int calculatetotalmoney() {
        int totalmoney = 0;
        for (Account acc : userAccounts) {

            totalmoney += acc.getBalance();


        }
        return totalmoney;

    }

    // Find an employee by registration number
    public NewEmploye findEmployeeByRegNo(String regNo) {
        for (NewEmploye emp : employees) {
            if (emp.regNo.equals(regNo)) {
                return emp;
            }
        }
        return null;
    }

    public NewEmploye findEmployeeByRegNo2(String regNo) {
        for (NewEmploye emp : employees) {
            if (emp.regNo.equals(regNo)) {
                System.out.println("emp info ->");
                emp.printEmpInfo();
            }
        }
        return null;
    }

    // Transfer money from one account to another
    public void transferMoney(int fromAccNo, int toAccNo, int amount) {
        Account fromAccount = findAccountByNumber(fromAccNo);
        Account toAccount = findAccountByNumber(toAccNo);
        if (fromAccount != null && toAccount != null) {
            fromAccount.transfer(toAccount, amount);
        } else {
            System.out.println("One or both account numbers are invalid.");
        }
    }


    // display manager manue

    public void displaymanager() {
        int op = 0;
        boolean exit = true;
        while (exit) {
            System.out.println("Manager section ->");
            System.out.println("1.check bank fund");
            System.out.println("2.check total loan given by bank ");
            System.out.println("3.Check employe details ");
            System.out.println("4.Remove employe");
            System.out.println("5.Check Invester details ");
            System.out.println("6.exit");

            op = errorhandle();

            switch (op) {
                case 1:

                    managerMenu();
                    break;
                case 2:
                    System.out.println("Total loan given by bank is  "+loans);
                    break;
                case 3:

                    System.out.println("enter registration no of employe :");
                    String regino = sc.nextLine();
                    findEmployeeByRegNo2(regino);
                    break;
                case 4:


                    removeemp();
                    break;
                case 5:
                    investerMenu();
                    break;
                case 6:
                    System.out.println("Returning to the bank control...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid option.");


            }

        }
    }

    private void removeemp() {
        System.out.println("ENTER EMP REGINO TO remove emp:");
        String reginno  = sc.nextLine();

            boolean bankFound = false;
            for (int i = 0; i <employees.size(); i++) {  // Iterate using index
                NewEmploye employe = employees.get(i);
                if (Objects.equals(reginno,employe.regNo)) {
                    employees.remove(i);  // Safe removal by index in ArrayList
                    bankFound = true;
                    System.out.println("Employe removed successfully.");
                    break;  // Exit loop after removal
                }
            }

            if (!bankFound) {
                System.out.println("Manager ID not found.");
            }
    }



// IDEA IDEA IDEA EK KAM KARO JO CHIG TTUN USER SE BARR BARR LE RAHE HO TAKI TUM CHECK KAR PAO KI ARRLIST ME VO HE KI NI
    // USKE BADLE EK BAR MANUE KE STARTING ME HI LE LO AND GLOBALLY STORE KARVA DO US FN OR METHODE ME ISSE
    // ISSE BARR BAR USER SE PUCHNA NI PADHE GAA KI TUMHARA NAME DALE VFERA VAGERA
    // OR VESE BHI HER BAR HUM JO METHODE USE KARE GE VO ALAG ALAG BANKS KE LIYE PURA METHODE BHI NAYA HI BANE GA EK TARIKE SE (PER KESE ?)


    public void bankststus(String nameofbank ,String ownername) {
        int op = 0;



        // a problem in opening this section

//        for (Bankowner owner : owners) {
//            if (Objects.equals(sno, owner.specialno)) {
//               String nam = (owner.name);
//
//            }
//        }

        boolean exit = true;
        while (exit) {
            System.out.println("Bank control section ->");
            System.out.println("Welcome to "+nameofbank+" bank :)");
            System.out.println("1. check bank details");
            System.out.println("2. Manager info");
            System.out.println("3. Add funds in bank ");
            System.out.println("4. Remove manager");
            System.out.println("5. Add new manager");
            System.out.println("6. See profits and loss of the bank");
            System.out.println("Note-> accounts and emp details are under manager controll");
            System.out.println("7. TAKE A LOOK OF YOUR BANK Mr."+ownername);
            System.out.println("8. close this bank ");
            System.out.println("Note-> to delete this bank got to main manue and select delet bank option  ");

            op = errorhandle();

            switch (op) {
                case 1:

                    for (Banks b : banks) {
                        if (Objects.equals(b.nameofbank, nameofbank)) {

                            b.bankinfo();
                        }
                    }
                    break;
                case 2:
                    System.out.println("enter manager id to see manager details");
                    String manno = sc.nextLine();
                    managerinfo(manno);
                    break;
                case 3:
                    addfund(nameofbank);
                    break;
                case 4:
                    Removeman(nameofbank);
                    break;
                case 5:
                    crateManger(nameofbank);
                    break;
                case 6:
                    System.out.println("COMEING SOON....");
                    break;
                case 7:
                    displayMainMenu(nameofbank,ownername);
                    break;
                case 8:
                    System.out.println("Returning to main menu...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid option.");


            }

        }


    }


    //'fund adding fn'\\
    public void addfund(String nofbank) {

        for (Banks bank : banks) {
            if (Objects.equals(nofbank, bank.nameofbank)) {
                System.out.println("Enter ammount to add :");
                int amm = sc.nextInt();
                bank.funds += amm;
                System.out.println("Now funds in bank is : " + bank.funds);

            }
        }
    }


//    public void skip(){
//
//    }

    // inside main manue
    public String profilemanue() {
        System.out.println("Profile section ->");
        // Adding a skip functionality to Skip from an option:
      //  System.out.println("To skip any of them enter Null (Currently working on it!) ");


        System.out.println("1. Enter name : ");
        String name = sc.nextLine();

        System.out.println("2. Enter Email : ");
        String Email = sc.nextLine();
        System.out.println("3. Enter Nickname");
        String nickname = sc.nextLine();
        System.out.println("4. Add links");
        String links = sc.nextLine();

        Profile profile = new Profile(name, Email, nickname, links);
        profiles.add(profile);
        System.out.println("you added you profile :)");
        System.out.println("you can check it or reset it further");
        return name;

    }
    private String setprofile(long mobileNo) {
        System.out.println("Set Profile section ->");

        // Search for the profile using the mobile number
        boolean found = false;
        String newName = null;
        for (Profile profile : profiles) {
            if (profileMatchesMobileNo(profile, mobileNo)) {
                found = true;

                // Ask for new values to update
                System.out.println("1. Enter new name (press Enter to skip): ");
                 newName = sc.nextLine();
                if (!newName.isEmpty()) {
                    profile.name = newName;  // Update name if provided
                }

                System.out.println("2. Enter new Email (press Enter to skip): ");
                String newEmail = sc.nextLine();
                if (!newEmail.isEmpty()) {
                    profile.Email = newEmail;  // Update email if provided
                }

                System.out.println("3. Enter new Nickname (press Enter to skip): ");
                String newNickname = sc.nextLine();
                if (!newNickname.isEmpty()) {
                    profile.nickname = newNickname;  // Update nickname if provided
                }

                System.out.println("4. Enter new links (press Enter to skip): ");
                String newLinks = sc.nextLine();
                if (!newLinks.isEmpty()) {
                    profile.links = newLinks;  // Update links if provided
                }

                System.out.println("Profile updated successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("No profile found for this mobile number.");
        }

        return newName;
    }

    // Helper method to check if the profile belongs to the user with the given mobile number
    private boolean profileMatchesMobileNo(Profile profile, long mobileNo) {
        for (Register reg : registers) {
            if (reg.phono == mobileNo) {
                return true;
            }
        }
        return false;
    }


    // make it to see only once after conection with database <- NOTE
    public void displayapp() {
        int option = 0;
        do {
            System.out.println("Welcome to E-Bank");
            System.out.println("1. New Registration with mobile no");
            System.out.println("2. login to an existing account");
            System.out.println("3. Help");
            System.out.println("4. About us");

            System.out.println("5. Close app ");
            option = errorhandle();
            switch (option) {
                case 1:
                    NewAppacount();
                    break;
                case 2:
                    Existingacc();
                    break;
                case 3:
                    gethelp();
                    break;
                case 4:
                    Aboutus();
                    break;


                case 5:
                    System.out.println("Thank you for using E-Bank.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    public void gethelp() {

        System.out.println("ask your query ");
        System.out.print("->");

    }

    public void Aboutus() {

        System.out.println("As you know that the world's first 'E-Bank' was invented by JLSS co-orporation");
        System.out.println("you can check more about us on our website the link is given below -> ");
        System.out.println("link");

    }

    private void Existingacc() {
        System.out.println("Enter moblie no to varify");
        long mobno = errorhandle2();
        FindRegisteredmobno(mobno);

    }

    public void FindRegisteredmobno(long mobno) {
        for (Register mob : registers) {
            if (mobno == mob.phono) {
                System.out.println("you loged in to your Existing acc ");
                System.out.println("Name of account is ");
                mainmanue(mobno);
                // now show main manue which contais following options

            } else {
                System.out.println("Moblie no not found in 'Database' ");
            }
        }

    }
    private void NewAppacount() {
        // Ask user for mobile number
        System.out.println("Enter mobile no: ");
        long phono = errorhandle2();  // Ensure errorhandle2 handles input correctly

        // Optional: Check if the mobile number already exists in registers (uncomment if needed)
    /*
    for (Register reg : registers) {
        if (phono == reg.phono) {
            System.out.println("You already have an existing account.");
            System.out.println("To login to that account, enter 'yes'. Otherwise, enter 'no': ");
            String ch = sc.nextLine();

            if (Objects.equals(ch, "yes")) {
                mainmanue();  // Redirect to the main menu
                return;       // Exit the method after redirecting
            } else if (Objects.equals(ch, "no")) {
                System.out.println("You selected 'no', so you want to add a new mobile number.");
                System.out.println("Enter new mobile no: ");
                phono = errorhandle2();  // Prompt again for a new mobile number
            }
        }
    }
    */

        // Generate OTP
        int otp = otpmake();
        System.out.println("OTP generated: " + otp);
        System.out.println("Enter OTP: ");
        int otps = errorhandle();  // Ensure errorhandle handles the OTP input correctly

        if (otps == otp) {
            System.out.println("You have successfully registered to E-Bank!");
            System.out.println("Now kindly create your profile.");

            // Create a new Register object and add to the 'registers' list

            Register Newreg = new Register(phono, otps);
            registers.add(Newreg);  // Add to the 'registers' list

            mainmanue(phono);  // Call the main menu after successful registration
        } else {
            System.out.println("OTP is incorrect. Try again or resend.");
        }
    }

    public int otpmake() {
        // Generate a random OTP (ensuring it's a 4-digit number)
        int otp = (int) (Math.random() * 10000);
        return otp;
    }


    // Display the main menu after creating bank
    public void displayMainMenu(String nameofbank , String nameofowner) {
        int option = 0;
        do {
            System.out.println("Welcome to E-Bank "+nameofbank+" of Mr."+nameofowner);

            System.out.println("1. User Menu");
            System.out.println("2. Employee Menu");
            System.out.println("3. Manager");
            System.out.println("4. Investor");

            System.out.println("5. Return to main manue ");
            option = errorhandle();
            switch (option) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 3:
                    displaymanager();
                    break;
                case 4:
                    investerMenu();
                    break;


                case 5:
                    System.out.println("Thank you for using E-Bank.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    int loan = 0;

    private void managerMenu() {
        DateandTime();
        int moneyinbank = calculatetotalmoney() + ammtoinvest;

        System.out.println("money in bank is : " + moneyinbank);


    }

    int ammtoinvest = 0;

    private void investerMenu() {
        DateandTime();
        int existingmoney = calculatetotalmoney();
        System.out.println("entert money to invest");
        ammtoinvest = sc.nextInt();
        existingmoney += ammtoinvest;
        System.out.println("you invested in JLSS E-bank of reupee : " + ammtoinvest);
        System.out.println("you toatal money in  JLSS E-bank of reupee : " + existingmoney);

    }
    int loans = 0;
    public void loan() {
        System.out.println("Enter associated Account No:");
        int accNo = errorhandle();
        Account account = findAccountByNumber(accNo);
        System.out.println("youor acc no is : " + account.getAccNo());
        System.out.println("enter loan ammount : ");
        loan = errorhandle();

        account.credit(loan);
        loans += loan;
        System.out.println("your acc is credited by loan of " + loan);
        System.out.println("Now balance is  " + account.getBalance());

    }

    private void DateandTime() {
        LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current local date and time
        int currentHour = currentDateTime.getHour(); // Extract the hour part of the time
        int currentDate = currentDateTime.getDayOfMonth(); // Extract the current day of the month

        if (currentHour < 12) {
            System.out.println("Good morning sir :)");
        } else {
            System.out.println("Good evening sir :)");
        }
        System.out.println("Today's date is: " + currentDate);
    }

    // User menu options
    private void userMenu() {
        int option = 0;
        do {
            System.out.println("User Menu");
            System.out.println("1. Create a new account");
            System.out.println("2. See account details");
            System.out.println("3. Search account by name");
            System.out.println("4. Search account by age");
            System.out.println("5. Credit money");
            System.out.println("6. Debit money");
            System.out.println("7. Transaction history");
            System.out.println("8. Transfer money");
            System.out.println("9. Apply for loan");
            System.out.println("10. Back to main menu");
            option = errorhandle();
            switch (option) {
                case 1:
                    createUserAccount();
                    break;
                case 2:
                    System.out.println("Enter Account No:");
                    int accNo = errorhandle();
                    Account acc = findAccountByNumber(accNo);
                    if (acc != null) {
                        acc.printInfo();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter name to search:");
                    String name = sc.nextLine();
                    for (Account account : userAccounts) {
                        if (account.name.equalsIgnoreCase(name)) {
                            account.printInfo();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter age to search:");
                    int age = errorhandle();
                    for (Account account : userAccounts) {
                        if (account.age == age) {
                            account.printInfo();
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter Account No:");
                    accNo = errorhandle();
                    acc = findAccountByNumber(accNo);
                    if (acc != null) {
                        System.out.println("Enter amount to credit:");
                        int amount = errorhandle();
                        acc.credit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter Account No:");
                    accNo = errorhandle();
                    acc = findAccountByNumber(accNo);
                    if (acc != null) {
                        System.out.println("Enter amount to debit:");
                        int amount = errorhandle();
                        acc.debit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 7:
                    System.out.println("Enter Account No:");
                    accNo = errorhandle();
                    acc = findAccountByNumber(accNo);
                    if (acc != null) {
                        acc.printTransactions();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 8:
                    System.out.println("Enter your Account No:");
                    int fromAccNo = errorhandle();
                    System.out.println("Enter recipient Account No:");
                    int toAccNo = errorhandle();
                    System.out.println("Enter amount to transfer:");
                    int amount = errorhandle();
                    transferMoney(fromAccNo, toAccNo, amount);
                    break;
                case 9:
                    loan();
                    break;
                case 10:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 10);
    }

    // Employee menu options
    private void employeeMenu() {
        int option = 0;
        do {
            System.out.println("Employee Menu");
            System.out.println("1. Register for job");
            System.out.println("2. Give Interview and get job");
            System.out.println("3. Check details");
            System.out.println("4. Check salary");
            System.out.println("5. Back to main menu");
            option = errorhandle();
            String regNo;
            switch (option) {
                case 1:
                    createEmployee();
                    break;
                case 2:
                    System.out.println("Enter your Registration ID:");
                    regNo = sc.nextLine();
                    NewEmploye emp = findEmployeeByRegNo(regNo);
                    if (emp != null) {
                        // Simulate interview and getting the job
                        System.out.println("Interview successful. You have been hired.");
                    } else {
                        System.out.println("Registration ID not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter your Registration ID:");
                    regNo = sc.nextLine();
                    emp = findEmployeeByRegNo(regNo);
                    if (emp != null) {
                        emp.printEmpInfo();
                    } else {
                        System.out.println("Registration ID not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter your Registration ID:");
                    regNo = sc.nextLine();
                    emp = findEmployeeByRegNo(regNo);
                    if (emp != null) {
                        System.out.println("1. Check salary details");
                        System.out.println("2. Check salary transactions");
                        System.out.println("3. Return to previous menu");
                        int salaryOption = errorhandle();
                        switch (salaryOption) {
                            case 1:
                                emp.printSalaryDetails();
                                break;
                            case 2:
                                emp.printSalaryTransactions();
                                break;
                            case 3:
                                System.out.println("Returning to previous menu...");
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    } else {
                        System.out.println("Registration ID not found.");
                    }
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 5);

    }

    boolean exit = true;
    // manue after registraion
    String check = "t";

    public void mainmanue(long registerd_mobileno) {

        long mobileno = registerd_mobileno;
        int op = 0;
        String tostorename = null;
        String tostorebankname = null;
        while (exit) {

            System.out.println("Welcome to your E-Bank main manue form here you can use whole app :)");
            if(check.equals("t")){
                System.out.println("1. Create profile");
            }else{
                System.out.println("1. Set your profile");
            }
            System.out.println("2. Create new bank");
            System.out.println("3. Add managers to bank");
            System.out.println("4. Open an existing bank ");
            System.out.println("5. See all the banks");
            System.out.println("6. Delete an existing bank");
            System.out.println("7. Log out from this acc of E-Bank");
            System.out.println("8. Subscribe to Primiume features of E-Bnak");
            System.out.println("9. Ask to JE Ai 'Chat bot' ");

            System.out.println("10. See profile");
            System.out.println("11. exit");
            op = errorhandle();


            switch (op) {

                    case 1:
                        if(Objects.equals(check, "f")){
                            tostorename =  setprofile(mobileno);
                        }else{

                           tostorename =  profilemanue();
                        }


                        check = "f";
                        break;
                case 2:
                    tostorebankname =  Createnewbank();
                    break;
                case 3:
                    crateManger(tostorebankname);
                    break;
                case 4:
                    openexistingbank();
                    break;
                case 5:
                    seeallbanks();
                    break;
                case 6:
                    deletebank();
                    break;
                case 7:
                    displayapp();
                    // show the registraion manue
                    // try to show it only once use do while loop and set condition to false;
                    break;
                case 8:
                    Primiume();

                    break;
                case 9:
                    Chatbot();

                    break;
                case 10:

                    Searching(tostorename);

                    break;
                case 11:
                    System.out.println("Returning to main menu...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid option.");

            }
        }
    }




    public void Primiume() {
        System.out.println("We offering you the best feature of E-bank");
        System.out.println("I. Aouto manage bank (no more manager's are needed ");
        System.out.println("II. No add's");
        System.out.println("III. create Unlimited banks");
        System.out.println("Iv. Ai(JE) use our fineset Ai moddle 'JE' ");
        System.out.println("For more details chose the options like 'I'");
        System.out.println(" ");
    }



    public void Searching(String tostorename) {
        for (Profile pro : profiles) {
            if (Objects.equals(tostorename, pro.name)) {
                System.out.println("your profile Details ->");
                pro.printpro();

            } else {
                System.out.println("EMAIL IS WRONG ");
            }
        }
    }

    public void seeallbanks() {
        System.out.println("Enter your profile name to varify");
        String pname = sc.nextLine();
        for (Profile in : profiles) {
            if (Objects.equals(pname, in.name)) {
                for (Banks banks1 : banks) {
                    banks1.bankinfo();
                    System.out.println("________________________________________");
                    // Try to use patterns to print info in boxes or in heart shape boundary to make app cool


                }

            }else {
                System.out.println("Profile name is wrong!");
            }

        }


    }

    public void Chatbot(){
        System.out.println("Askyour quee to JE ->");
        String quee = sc.nextLine();

        System.out.println("AI is under work");
    }


}




