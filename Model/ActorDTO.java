package Model;

public class ActorDTO {
    private int writer;
    private int id;
    private String name;
    private int birthdayY;
    private int birthdayM;
    private int birthdayD;
    private int year;
    private String firstMov;
    private String family;

    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthdayY() {
        return birthdayY;
    }

    public int getBirthdayM() {
        return birthdayM;
    }

    public void setBirthdayM(int birthdayM) {
        this.birthdayM = birthdayM;
    }

    public int getBirthdayD() {
        return birthdayD;
    }

    public void setBirthdayD(int birthdayD) {
        this.birthdayD = birthdayD;
    }

    public void setBirthdayY(int birthdayY) {
        this.birthdayY = birthdayY;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFirstMov() {
        return firstMov;
    }

    public void setFirstMov(String firstMov) {
        this.firstMov = firstMov;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public boolean equals(Object o) {
        if (o instanceof ActorDTO) {
            ActorDTO a = (ActorDTO) o;
            return id == a.id || (a.name.equals(name) & year == a.year && birthdayY == a.birthdayY);
        }

        return false;
    }

    public ActorDTO() {

    }

    public ActorDTO(ActorDTO a) {
        writer = a.writer;
        id = a.id;
        name = new String(a.name);
        birthdayY = a.birthdayY;
        birthdayM = a.birthdayM;
        birthdayD = a.birthdayD;
        year = a.year;
        firstMov = new String(a.firstMov);
        family = new String(a.family);

    }

}
