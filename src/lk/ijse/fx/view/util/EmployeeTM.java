package lk.ijse.fx.view.util;

public class EmployeeTM {

    private String employee_id;
    private String employee_name;
    private String address;
    private int age;
    private String mobile;
    private String job;
    private String gender;

    public EmployeeTM() {
    }

    public EmployeeTM(String employee_id, String employee_name, String address, int age, String mobile, String job, String gender) {
        this.setEmployee_id(employee_id);
        this.setEmployee_name(employee_name);
        this.setAddress(address);
        this.setAge(age);
        this.setMobile(mobile);
        this.setJob(job);
        this.setGender(gender);
    }


    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "employee_id='" + employee_id + '\'' +
                ", employee_name='" + employee_name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", job='" + job + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
