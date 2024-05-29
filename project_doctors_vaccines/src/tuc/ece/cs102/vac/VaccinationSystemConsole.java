//Christos Kostadimas 
package tuc.ece.cs102.vac;

//importing packages
import java.util.Scanner;

public class VaccinationSystemConsole {

    /*Main Class*/
    public static void main(String[] args) {

        VaccinationSystem vac = new VaccinationSystem("System", "0");

        /*Adding the insured*/
        vac.addInsured(new Insured("11111111", "Asfalismenos1", "Chania"));
        vac.addInsured(new Insured("22222222", "Asfalismenos2", "Chania"));
        vac.addInsured(new Insured("33333333", "Asfalismenos3", "Chania"));
        vac.addInsured(new Insured("44444444", "Asfalismenos4", "Chania"));
        vac.addInsured(new Insured("55555555", "Asfalismenos5", "Chania"));
        vac.addInsured(new Insured("66666666", "Asfalismenos6", "Rethimno"));
        vac.addInsured(new Insured("77777777", "Asfalismenos7", "Rethimno"));
        vac.addInsured(new Insured("88888888", "Asfalismenos8", "Rethimno"));
        vac.addInsured(new Insured("99999999", "Asfalismenos9", "Rethimno"));

        /*Adding the Vaccination Centers*/
        VaccineCenter center1 = new VaccineCenter("22222", "CH-22", "Chania");
        VaccineCenter center2 = new VaccineCenter("33333", "RTH-33", "Rethimno");
        vac.addVaccineCenter(center1);
        vac.addVaccineCenter(center2);

        /*Adding the doctors to their centers*/
        Doctor doct1 = new Doctor("111111", "Doctor1");
        Doctor doct2 = new Doctor("222222", "Doctor2");
        Doctor doct3 = new Doctor("333333", "Doctor3");
        Doctor doct4 = new Doctor("444444", "Doctor4");
        Doctor doct5 = new Doctor("555555", "Doctor5");

        /*Adding each doctor to his center*/
        center1.addDoctor(doct1);
        center1.addDoctor(doct2);
        center1.addDoctor(doct3);
        center2.addDoctor(doct4);
        center2.addDoctor(doct5);

        /*
		 * Adding the appointments
		 * using the addNewAppointment2 (exists in VaccineCenter class)
		 * this method adds an appointment that has a specific KAP and not a RANDOM kap.
         */
        center1.addNewAppointment2("20", 0, 0, vac.findInsuredByAmka("11111111"), doct1, center1);
        center1.addNewAppointment2("21", 0, 3, vac.findInsuredByAmka("22222222"), doct1, center1);
        center1.addNewAppointment2("22", 0, 3, vac.findInsuredByAmka("33333333"), doct2, center1);
        center1.addNewAppointment2("23", 0, 3, vac.findInsuredByAmka("44444444"), doct3, center1);
        center1.addNewAppointment2("24", 1, 1, vac.findInsuredByAmka("55555555"), doct1, center1);
        center2.addNewAppointment2("30", 1, 1, vac.findInsuredByAmka("66666666"), doct4, center2);
        center2.addNewAppointment2("31", 1, 1, vac.findInsuredByAmka("77777777"), doct5, center2);
        center2.addNewAppointment2("32", 1, 2, vac.findInsuredByAmka("88888888"), doct5, center2);
        center2.addNewAppointment2("33", 2, 1, vac.findInsuredByAmka("99999999"), doct4, center2);

        /**
         * ************************Printing the Menu*************************
         */
        Scanner input = new Scanner(System.in);
        int userChoise = 0;
        //Scanner input = new Scanner(System.in);
        String fullName, amka, city, title, code, am;
        int dayChoice, timeChoice; //for storing the data that the user gave 

        do {
            printMenu();
            System.out.print("What would you like to do?");
            String userChoiseString = input.next();
            if (userChoiseString == null) {
                continue;
            } else {
                try {
                    userChoise = Integer.parseInt(userChoiseString);
                } catch (NumberFormatException ex) {
                    userChoise = 0;
                }
            }
            /*storing users input */
            switch (userChoise) {

                case 1:
                    System.out.print("Give Insured Full Name:");
                    fullName = input.next();
                    System.out.print("Give the amka of the insured:");
                    amka = input.next();
                    System.out.print("Give the city that the insured lives at:");
                    city = input.next();
                    vac.addInsured(new Insured(amka, fullName, city)); //Importing the insured..
                    System.out.println("Press any number to continue...");
                    input.next();
                    break;

                case 2:
                    System.out.print("Give the code of the Vaccination Center:");
                    code = input.next();
                    System.out.print("Give the title of the Vaccination Center:");
                    title = input.next();
                    System.out.print("Give the city of the Vaccination Center:");
                    city = input.next();
                    vac.addVaccineCenter(new VaccineCenter(code, title, city));
                    System.out.println("Press any number to continue...");
                    input.next();
                    break;

                case 3:
                    //importing doctor
                    //Getting the needed data from the user
                    System.out.println("The available centers that you can enroll the doctor are: \n");
                    vac.printCenters();
                    System.out.print("Give the doctors am:");
                    am = input.next();
                    System.out.print("Give the doctors fullname:");
                    fullName = input.next();
                    System.out.print("Give the doctor's vaccine center's code:");
                    code = input.next();
                    System.out.print("Give the doctor's vaccine center's title:");
                    title = input.next();
                    System.out.print("Give the doctor's vaccine center's city:");
                    city = input.next();

                    /*generating the doctor the user entered*/
                    Doctor doc = new Doctor(am, fullName);

                    /*1)One center in each city!*/
 /*2)if the vaccination center the user entered already exists then i am importing doctor in the catalog of doctors*/
 /*3)if the vaccination center the user entered does not exist then he must enter it in case 2*/
                    VaccineCenter cent = new VaccineCenter();
                    int flag = 1;
                    for (int i = 0; i < vac.getNumOfCenters(); i++) {
                        cent = vac.getVaccineCenter()[i];
                        if (cent != null) {
                            if (city.equals(cent.getCenterCity())) {
                                cent.addDoctor(doc);
                                flag = 0;
                                System.out.println("The doctor has succesfully been entered to the existing center!");
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        System.out.println("The center you just entered does not exist, go back to choice (2) and enter it!");
                    }
                    System.out.println("Press any number to continue...");
                    input.next();
                    break;

                case 4:
                    Insured insured1 = new Insured();
                    System.out.print("Give  the amka of the insured you want to search:");
                    amka = input.next();
                    insured1 = vac.findInsuredByAmka(amka);
                    if (insured1 != null) {
                        VaccineCenter center3 = new VaccineCenter();
                        center3 = vac.findCenterByCity(insured1.getCity());
                        if (center3 != null) {
                            for (Doctor d : center3.getArrayOfDoc()) {
                                if (d != null) {
                                    do {
                                        vac.printAvailableApp(center3);
                                        printChoice();
                                        System.out.print("Give me the day you want to arrange the appointment");
                                        dayChoice = input.nextInt();
                                        System.out.print("Give me the time you want to arrange the appointment");
                                        timeChoice = input.nextInt();
                                        if (timeChoice >= 4 && dayChoice > 7) {
                                            System.out.println("Wrong input. Please try again.");
                                            break;
                                        } else if (vac.checkAppointment(dayChoice, timeChoice, center3) == 1) {																		//if(vac.checkAppointment(timeChoice, dayChoice) != 1) *{
                                            center3.addNewAppointment(dayChoice, timeChoice, insured1, center3);
                                            break;
                                        } else {
                                            System.out.println("APPOINTMENT IS BOOKED!");
                                        }
                                    } while (vac.checkAppointment(dayChoice, timeChoice, center3) != 1);
                                } else {
                                    System.out.println("No doctor detected to do the vaccine, please enter doctor by choosing 3 in the menu");
                                }
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    System.out.println("Press any number to continue...");
                    input.next();
                    break;

                case 5:
                    int userChoice2 = 0;
                    printChoiceToFindAppointment();
                    System.out.print("What would you like to do?");
                    String userChoiceString2 = input.next();
                    if (userChoiceString2 == null) {
                        continue;
                    } else {
                        try {
                            userChoice2 = Integer.parseInt(userChoiceString2);
                        } catch (NumberFormatException ex) {
                            userChoice2 = 0;
                        }
                    }
                    switch (userChoice2) {

                        case 1:
                            Insured insured2 = new Insured();
                            System.out.print("Give  the amka of the insured you want to search his appointment:");
                            amka = input.next();
                            insured2 = vac.findInsuredByAmka(amka);
                            if (insured2 != null) {
                                VaccineCenter center4 = new VaccineCenter();
                                center4 = vac.findCenterByCity(insured2.getCity());
                                center4.findAppointmentByAmka(amka);
                                break;
                            }
                        case 2:
                            VaccineCenter center5 = new VaccineCenter();
                            System.out.print("Give the center's title");
                            String title1 = input.next();
                            center5 = vac.findCenterByTitle(title1);
                            if (center5 != null) {
                                center5.findAppointmentByCentersTitle(center5);
                                break;
                            }
                            break;
                        case 3:
                            System.out.print("Give the doctor's amka");
                            am = input.next();
                            vac.findAppointmentByDoctorAm(am); //also prints the appointment!
                            break;
                    }
                    System.out.println("Press any number to continue...");
                    input.next();
                    break;

                case 6:
                    vac.printAllInsured();
                    break;

                case 7:
                    vac.printCenters();
                    break;

                case 8:
                    vac.printAllDoctors();
                    break;

                case 9:
                    System.out.println("PROGRAM TERMINATED");
            }

        } while (userChoise != 9 && userChoise != 0);
        input.close();
    }

    /**
     * Method that prints the System Console*
     */
    public static void printMenu() {
        System.out.println("-----------------------------------------[Menu]-------------------------------------------");
        System.out.println("1. Import Insured!........................................................................");
        System.out.println("2. Import Vaccination Center .............................................................");
        System.out.println("3. Import doctor..........................................................................");
        System.out.println("4. Program new appointment................................................................");
        System.out.println("5. Find and print appointment.............................................................");
        System.out.println("6. Print all insured of the system........................................................");
        System.out.println("7. Print all vaccine centers..............................................................");
        System.out.println("8. Print all doctors......................................................................");
        System.out.println("9. Exit...................................................................................");
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public static void printChoice() {
        System.out.println("-------------------------------------[Appointments]---------------------------------------");
        System.out.println("-----What time and in how many days from now , do you want to book a new appointment------");
        System.out.println(".....................................D A Y S..............................................");
        System.out.println("(0)...........(1)...........(2)...........(3)............(4)............(5)............(6)");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(".....................................H O U R S............................................");
        System.out.println("9.00/(0)................. 9.30/(1) ................. 10.00/(2) ................. 10.30/(3)");
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public static void printChoiceToFindAppointment() {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Press 1 to find appointment by insured AMKA................................................");
        System.out.println("Press 2 to find appointment by Vaccination Center's title..................................");
        System.out.println("Press 3 to find appointment by doctors AM..................................................");
        System.out.println("-------------------------------------------------------------------------------------------");
    }
}
