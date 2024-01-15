package projectCPS2232;

import java.util.ArrayList;
import java.util.HashSet;

public class Staff extends Zoo{
    private String phone;

    private HashSet<Animal> keepAnimals;


    public Staff(String name) {
        setType("Human");
        setName(name);
        setId(generateID());
        keepAnimals = new HashSet<>();
        System.out.println(this);
    }

    // Getters and setters for staff properties
    public HashSet<Animal> getKeepAnimals() {
        return keepAnimals;
    }

    // add animal to staff's keep animals, and set animal's keeper as staff
    public void addKeepAnimals(Animal animal) {
        if (animal.getKeeper() != null && animal.getKeeper().equals(this)) {
            keepAnimals.add(animal);
        } else {
            animal.setKeeper(this);
        }
    }

    // remove animal from staff's animal sheet, and set the animals' keeper as null
    public boolean removeKeepAnimals(Animal animal) {
        if (keepAnimals.contains(animal)) {
            keepAnimals.remove(animal);
            animal.setKeeper(null);
            return true;
        } else {
            return false;
        }
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    //delete the staff throughtly
    @Override
    public void remove() {
        if (containId(getId())) {
            for (Animal animal : getKeepAnimals()) {
                animal.setKeeper(null);
                super.remove();
                System.out.println(getName() + " remove successfully");
            }
        } else {
            System.out.println(getName() + " not exists");
        }
    }
    @Override
    public long generateID() {
        long temp= super.generateID() + 1;
        addId(temp, this);
        return temp;
    }

    @Override
    public String toString() {
        String animals = "";
        for(Animal animal: getKeepAnimals())
            animals += animal.toString();
        return "\nStaff{" +
                "\nname: " + getName()
                + "\nphone= " + getPhone() +
                "\nkeep Animals: " + animals +
                "\nStaff ID:" + getId() + "\n}\n";
    }

}

