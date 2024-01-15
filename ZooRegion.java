package projectCPS2232;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ZooRegion extends Zoo{
    private HashSet<Animal> animalSheet;
    private HashSet<Staff>   staffSheet;
    // the static hashset to store all existed Zoo region


    // ban constructor to emphasize the meaning of a zoo region
    private ZooRegion(String name) {
        setType("ZooRegion");
        setName(name);
        this.animalSheet = new HashSet<>();
        this.staffSheet = new HashSet<>();
        setId(generateID());
        System.out.println(this);
    }

    // method to create a new zoo region
    public static ZooRegion createNewZooRegion(String name) {
        return new ZooRegion(name);
    }

    public static ZooRegion createNewZooRegion(String name, HashSet<Animal> animalSheet, HashSet<Staff> staffSheet) {
        ZooRegion temp = new ZooRegion(name);
        temp.staffSheet = staffSheet;
        temp.animalSheet = animalSheet;
        return temp;
    }
    // Getters and setters for region properties


    // Method to add an animal to the region, will add the animal's staff to staff animal
    public void addAnimal(Animal animal) {
        if (animal.getZooRegion() != null && animal.getZooRegion().equals(this)) {
            animalSheet.add(animal);
            addStaff(animal.getKeeper());
        } else {
            animal.setZooRegion(this);
        }

    }

    // Method to remove an animal from the region,
    // and remove staff from the region if the staff do not keep other this region's animal
    public void removeAnimal(Animal animal) {
        if (animalSheet.contains(animal)) {
            animalSheet.remove(animal);
            animal.setZooRegion(null);
            System.out.println(animal.getName() + " removed from " + getName());
        }else {
            System.out.println(animal.getName() + " not existed in" + getName());
        }


    }

    // method to add staff to this region
    public void addStaff(Staff staff) {
        if(!staffSheet.contains(staff)) {
            staffSheet.add(staff);
            System.out.println(staff.getName() + " added to " + getName());
        }else {
            System.out.println(staff.getName() + " has existed in " + getName());
        }
    }

    // Method to remove an staff from the region, and remove
    public void removeStaff(Staff staff) {
        if(staffSheet.contains(staff)) {
            boolean canRemove = true;
            for(Animal animal : animalSheet){
                Staff temp = animal.getKeeper();
                if(temp != null && temp.equals(staff))
                    canRemove =false;
            }
            if (canRemove) {
                staffSheet.remove(staff);
                System.out.println(staff.getName() + " removed from " + getName());
            }
        }else {
            System.out.println(staff.getName() + " not existed in" + getName());
        }
    }




    @Override
    public void remove() {
        if (containId(getId())) {
            for (Animal animal : animalSheet) {
                animal.setZooRegion(null);
                super.remove();
                System.out.println(getName() + " remove successfully");
            }
        } else {
            System.out.println(getName() + " not exists");
        }
    }
    @Override// zero represent as zoo region
    public long generateID() {
        long temp= super.generateID() + 0;
        addId(temp, this);
        return temp;
    }
    @Override
    public String toString() {
        String str1 = "";
        String str2 = "";
        for (Animal animal : animalSheet) {
            str1 += animal.toString();
        }

        for (Staff staff : staffSheet) {
            str2 += "\nStaff{" +
                    "\nname: " + staff.getName()
                    + "\nphone= " + staff.getPhone() +
                    "\nStaff ID:" + getId() + "\n}\n";
        }
        return "\nZooRegion{\n" +
                getName() + "'s zoo region animals: " + str1 + "\n" +
        getName() + "'s zoo region staffs: " + str2 + "\n}\n";

    }

}

