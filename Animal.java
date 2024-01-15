package projectCPS2232;

public class Animal extends Zoo{
    private Staff keeper;
    private ZooRegion zooRegion;

    public Animal(String name, String species) {
        this(name, species, null);
    }

    // start with name species and keeper
    public Animal(String name, String species, Staff keeper) {
        setName(name);
        setType(species);
        setKeeper(keeper);
        setId(generateID());
        System.out.println(this);
    }

    public ZooRegion getZooRegion() {
        return zooRegion;
    }

    // set the animal's zoo region and add animal's keeper to the zoo region
    public void setZooRegion(ZooRegion zooRegion) {
        if (getZooRegion() != null) {
            ZooRegion temp = getZooRegion();
            getZooRegion().removeAnimal(this);
            if(getKeeper() != null)
                temp.removeStaff(getKeeper());
        }
        if (zooRegion != null) {
            this.zooRegion = zooRegion;
            zooRegion.addAnimal(this);
            System.out.println(getName() + "'s ZooRegion set successfully");
        } else {
            this.zooRegion =null;
            System.out.println(getName() + "'s ZooRegion set as null");
        }
    }


    public Staff getKeeper() {
        return keeper;
    }

    // will set this animal's keeper, and add animal to staff's keep animal sheet
    public void setKeeper(Staff keeper) {
        if (getKeeper() != null) {
            Staff temp = getKeeper();
            getKeeper().removeKeepAnimals(this);
            if(getZooRegion() != null)
                getZooRegion().removeStaff(temp);
        }
        if (keeper != null) {
            this.keeper = keeper;
            this.keeper.addKeepAnimals(this);
            if(getZooRegion() != null){
                getZooRegion().addStaff(getKeeper());
            }
            System.out.println(getName() + "'s keeper set successfully");
        } else {
            this.keeper = null;
            System.out.println(getName() + "'s keeper set as null");
        }
    }


    // to remove the animal throughtly
    @Override
    public void remove() {
        if (containId(getId())) {
            setZooRegion(null);
            setKeeper(null);
            super.remove();
            System.out.println(getName() + " remove successfully");
        } else {
            System.out.println(getName() + " not exists");
        }
    }
    @Override  // add two to show it is animal
    public long generateID() {
        long temp= super.generateID() + 2;
        addId(temp, this);
        return temp;

    }
    @Override
    public String toString() {
        return "\nAnimal{\nname: " + getName() +
                ", species= " + getType() +
                ", keeper: " + (getKeeper() == null? null :getKeeper().getName()) +
                ", Animal ID:" + getId() + "\n}\n";
    }

}

