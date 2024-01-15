package projectCPS2232;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ZooManagementSystem {
    public static void main(String[] args) {
        Initial();
    }
    public static void Initial(){
        System.out.println("Option1:Animal Management; Option2:Staff Management; Option3:Region Management; Option4:SaveAndQuit");
        //Input option
        System.out.println("Please input your option: with number 1,2,3,4");
        int option = limitNumberInput(new Scanner(System.in),4);
        if (option == 1){
            AnimalManagement();
        }
        else if (option == 2){
            StaffManagement();
        }
        else if (option == 3){
            //RegionManagement();
        }
        else if (option == 4){
            System.out.println("Save and Quit");
        }
    }
    public static void RegionManagement(){
        while (true){
            System.out.println("Option1:Add a Region; Option2:Delete a Region; Option3:DisplayAllRegion; Option4:SaveAndQuit");
            System.out.println("Please input your option: with number 1,2,3,4");
            int option = limitNumberInput(new Scanner(System.in),4);
            String name;
            Scanner sc = new Scanner(System.in);
            if (option == 1){
                System.out.println("Input name");
                //Create a new Region
                name=sc.next();
                //Write Region's information into txt file
                AddRegionInfo(name);
            }
            else if (option == 2){
                System.out.println("Input the name of Region you want to delete");
                name=sc.next();
                DeleteRegionInfo(name);
            }
            else if (option == 4){
                System.out.println("Save and Quit");
                //Close the TxT file
                Initial();//Back to the initial page
                //...
            }else if (option == 3){
                DisplayAllRegion();
                //Display all regions
            }
        }
    }

    private static void DisplayAllRegion() {
    }

    private static void DeleteRegionInfo(String regionName) {
    }

    private static void AddRegionInfo(String regionName) {
    }

    private static void StaffManagement() {
        while (true){
            System.out.println("Option1:Add a Staff; Option2:Delete a Staff; Option3:Search; Option4:SaveAndQuit");
            System.out.println("Please input your option: with number 1,2,3,4");
            int option = limitNumberInput(new Scanner(System.in),4);
            String name;
            Scanner sc = new Scanner(System.in);
            if (option == 1){
                System.out.println("Input name, phone number");
                //Create a new Staff
                name=sc.next();
                String phone=sc.next();
                //Write staff's information into txt file
                AddStaffInfo(name,phone);
            }
            else if (option == 2){
                System.out.println("Input the name of Staff you want to delete");
                name=sc.next();
                DeleteStaffInfo(name);
            }
            else if (option == 4){
                System.out.println("Save and Quit");
                //Close the TxT file
                Initial();//Back to the initial page
                //...
            }else if (option == 3){
                System.out.println("Option1:ByName; Option2:ByID; Option3:ByPhone, Option4:AnimalID; Option5:AnimalName");
                int option3 = limitNumberInput(new Scanner(System.in),5);
                if (option3==1){
                    name = sc.next();
                    SearchStaffInfoByName(name);
                }else if (option3==2){
                    int number = sc.nextInt();
                    SearchStaffInfoByStaffID(number);
                } else if (option3==3){
                    long phone = sc.nextLong();
                    SearchStaffInfoByPhone(phone);
                } else if (option3==4) {
                    int AnimalID = sc.nextInt();
                    SearchStaffInfoByAnimalID(AnimalID);
                } else if (option3==5){
                    name = sc.next();
                    SearchStaffInfoByAnimalName(name);
                }
            }
        }

    }
    //Check if the input is valid.
    public static int limitNumberInput(Scanner scanner,int i){ //limit input make it a number between 1~i
        int c;
        while (true) {
            String str = scanner.next();
            if(str.length()==1){
                for (int j = 1; j <= i; j++) {
                    if(str.equals(j+"")){
                        return j;
                    }
                }
            }
            System.out.print("Invalid Input!");
        }
    }
    //Animal Management
    public static void AnimalManagement(){
        while (true){
            System.out.println("Option1:Add an Animal; Option2:Delete an Animal; Option3:Search; Option4:SaveAndQuit");
            System.out.println("Please input your option: with number 1,2,3,4");
            int option = limitNumberInput(new Scanner(System.in),4);
            String name;
            Scanner sc = new Scanner(System.in);
            if (option == 1){
                System.out.println("Input name, specie name");
                //Create a new Animal
                name=sc.next();
                String specie=sc.next();
                //Write animal's information into txt file
                AddAnimalInfo(name,specie);
            }
            else if (option == 2){
                System.out.println("Input the name of Animal you want to delete");
                name=sc.next();
                DeleteAnimalInfo(name);
            }
            else if (option == 4){
                System.out.println("Save and Quit");
                //Close the TxT file
                Initial();//Back to the initial page
                //...Close TXT and refresh the page
            }else if (option == 3){
                System.out.println("Option1:ByName; Option2:ByID; Option3:BySpecie; Option4:ByRegion; Option5:ByKeeperName; Option6:ByKeeperID");
                int option3 = limitNumberInput(new Scanner(System.in),6);
                if (option3==1){
                    name = sc.next();
                    SearchAnimalInfoByName(name);
                }else if (option3==2){
                    int number = sc.nextInt();
                    SearchAnimalInfoByAnimalID(number);
                } else if (option3==3){
                    name = sc.next();
                    SearchAnimalInfoBySpecie(name);
                } else if (option3==4){
                    name = sc.next();
                    SearchAnimalInfoByRegion(name);
                } else if (option3==5){
                    name = sc.next();
                    SearchAnimalInfoByStaffName(name);
                } else if (option3==6){
                    int StaffNumber = sc.nextInt();
                    SearchAnimalInfoByStaffID(StaffNumber);
                }
            }
        }
    }
    public static void AddStaffInfo(String name, String phone){
        Staff staff = new Staff(name);
        staff.setPhone(phone);
    }
    public static void AddAnimalInfo(String name,String specie){
        Animal animal = new Animal(name, specie);
    }
    public static void DeleteStaffInfo(String name){
        HashMap<Long, Zoo> staffSheetTemp = Sheet.searchByName(name, Sheet.STAFF_KEY);
        System.out.println("Search result following: ");
        for (Map.Entry<Long, Zoo> entry : staffSheetTemp.entrySet()) {
            System.out.print(entry.getValue());
        }
        chooseOneToDelete(name, staffSheetTemp);
    }
    public static void DeleteAnimalInfo(String name){
        HashMap<Long, Zoo> animalSheetTemp = SearchAnimalInfoByName(name);
        chooseOneToDelete(name, animalSheetTemp);
    }
    public static HashMap<Long, Zoo> SearchAnimalInfoByName(String name){
        HashMap<Long, Zoo> animalSheetTemp = Sheet.searchByName(name, Sheet.ANIMAL_KEY);
        System.out.println("Search similar result following: ");
        for (Map.Entry<Long, Zoo> entry : animalSheetTemp.entrySet()) {
            System.out.print(entry.getValue());
        }return animalSheetTemp;
    }
    public static void SearchAnimalInfoByAnimalID(long animalID){
        HashMap<Long, Zoo> animalSheetTemp = Sheet.searchById(animalID, Sheet.ANIMAL_KEY);
        System.out.println("Search similar result following: ");
        for (Map.Entry<Long, Zoo> entry : animalSheetTemp.entrySet()) {
            System.out.print(entry.getValue());
        }
    }
    public static void SearchAnimalInfoBySpecie(String specieName){
        HashMap<Long, Zoo> animalSheetTemp = Sheet.searchAnimalBySpecies(specieName);
        System.out.println("Search result following: ");
        for (Map.Entry<Long, Zoo> entry : animalSheetTemp.entrySet()) {
            System.out.print(entry.getValue());
        }
    }
    public static void SearchAnimalInfoByRegion(String regionName){
//        HashMap<Long, Zoo> zooRegionSheetTemp = SearchTool.searchByName(regionName, SearchTool.ZOOREGION_KEY);
//        System.out.println("Search result following: ");
//        for (Map.Entry<Long, Zoo> entry : zooRegionSheetTemp.entrySet()) {
//            System.out.println("Zoo region name: " + entry.getValue().getName() +
//                    "Zoo region id: "+ entry.getValue().getId());
//        }
//        System.out.println("There are many similar name, please enter the id to show the zoo region is: ");
//        Scanner input = new Scanner(System.in);
//        long chooseId = input.nextLong();
//        if (zooRegionSheetTemp.containsKey(chooseId) && zooRegionSheetTemp.get(chooseId) instanceof ZooRegion) {
//            System.out.println(zooRegionSheetTemp.get(chooseId));//may have wrong here, need to check because it show get(Object o )
//            System.out.println("get " + name + " successfully");
//        } else {
//            System.out.println("Delete " + name + " failed");
//        }
//        暂停处，该写GE2024 final了，时间紧迫，此处托付给你们了
    }
    public static void SearchAnimalInfoByStaffName(String staffName){

    }
    public static void SearchAnimalInfoByStaffID(int StaffID){

    }
    private static void SearchStaffInfoByAnimalName(String animalName) {
    }

    private static void SearchStaffInfoByAnimalID(int animalID) {

    }

    private static void SearchStaffInfoByPhone(long staffPhoneNumber) {

    }

    private static void SearchStaffInfoByStaffID(long staffID) {
        HashMap<Long, Zoo> staffSheetTemp = Sheet.searchById(staffID, Sheet.STAFF_KEY);
        System.out.println("Search similar result following: ");
        for (Map.Entry<Long, Zoo> entry : staffSheetTemp.entrySet()) {
            System.out.print(entry.getValue());
        }
    }

    private static void SearchStaffInfoByName(String staffName) {
        HashMap<Long, Zoo> staffSheetTemp = Sheet.searchByName(staffName, Sheet.STAFF_KEY);
        System.out.println("Search similar result following: ");
        for (Map.Entry<Long, Zoo> entry : staffSheetTemp.entrySet()) {
            System.out.print(entry.getValue());
        }
    }

    private static void chooseOneToDelete(String name, HashMap<Long, Zoo> sheet) {
        System.out.println("There are many similar name, please enter the id to delete it: ");
        Scanner input = new Scanner(System.in);
        long deleteId = input.nextLong();
        if (sheet.containsKey(deleteId)) {
            sheet.get(deleteId).remove();//may have wrong here, need to check because it show get(Object o )
            System.out.println("Delete " + name + " successfully");
        } else {
            System.out.println("Delete " + name + " failed");
        }
    }
}
