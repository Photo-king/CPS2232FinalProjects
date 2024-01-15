package projectCPS2232;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.HashMap;

public class Zoo {
    private String name;
    private String type;
    private long id= 0;
    /* provide a static hashset to store ids */
    private static HashMap<Long, Zoo> ids = new HashMap<>();

    // represent as species
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static HashMap<Long, Zoo> getIds() {
        return ids;
    }

    /* generate general id with year + month + this time's second  + random (0-999) number */
    protected   long generateID() {
        //this method need to override to add a number in last number
        // , to represent is animal(2), staff(1) or Zooregion(0)
        Calendar calendar = Calendar.getInstance();
        long year = calendar.get(Calendar.YEAR);
        long month = calendar.get(Calendar.MONTH);
        long second = calendar.get(Calendar.SECOND);
        long id = (year * 100 + month ) * 100 + second;
        SecureRandom secureRandom = new SecureRandom();
        long temp = 0;
        do{
            temp = (id * 10000 + secureRandom.nextInt(9999))*10;
        }while(ids.containsKey(temp) || ids.containsKey(temp + 1)  || ids.containsKey(temp + 2) );
        return temp;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // need to override to delete all information, will remove the from zoo throughtly
    protected void remove() {
        if (ids.containsKey(id)) {
            ids.remove(id);
            System.out.println(getId() + " remove from ids successfully");
        }else {
            System.out.println(getId() + " not exists");
        }
    }

    protected void addId(long id, Zoo zoo) {
        ids.put(id, zoo);
    }

    // a static method to check whether the id is in the ids
    public static boolean containId(long id) {
        return ids.containsKey(id);
    }


    // compare whether the id is the same
    @Override
    public boolean equals(Object o) {
        try {
            return getId() == ((Zoo) o).getId();
        } catch (Exception exception) {
            return false;
        }
    }
}
