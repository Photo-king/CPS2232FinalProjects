package projectCPS2232;

public class Visitor {
    private String name;
    private int age;
    private String ticketType;

    public Visitor(String name, int age, String ticketType) {
        this.name = name;
        this.age = age;
        this.ticketType = ticketType;
    }

    // Getters and setters for visitor properties

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    // Other methods related to visitor information and operations can be added here
}

