package Model;

public class Contact {
    int contact_ID;
    String contact_Name;
    String contact_Email;

    public Contact(int contact_ID, String contact_Name, String contact_Email) {
        this.contact_ID = contact_ID;
        this.contact_Name = contact_Name;
        this.contact_Email = contact_Email;
    }

    public int getContact_ID() {
        return contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    public String getContact_Name() {
        return contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        this.contact_Name = contact_Name;
    }

    public String getContact_Email() {
        return contact_Email;
    }

    public void setContact_Email(String contact_Email) {
        this.contact_Email = contact_Email;
    }
}
