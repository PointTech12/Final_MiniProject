public class Contact {
    private int id;
    private String name;
    private String phone;
    private String mobile;
    private String email;
    private String address;
    private String company;
    private String position;
    private String groupName;

    public Contact(int id, String name, String phone, String mobile, String email,
                   String address, String company, String position, String groupName) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.company = company;
        this.position = position;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getGroupName() {
        return groupName;
    }
}
