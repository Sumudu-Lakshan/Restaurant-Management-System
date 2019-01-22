package lk.ijse.fx.dao.custom.impl;

import lk.ijse.fx.dao.CrudUtil;
import lk.ijse.fx.dao.custom.EmployeeDAO;
import lk.ijse.fx.entity.Employee;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public Optional<Employee> find(String employee_id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Employee WHERE employee_id=?", employee_id);
        Employee e = null;
        if (rst.next()) {
            e = new Employee(rst.getString("employee_id"),
                                 rst.getString("employee_name"),
                                     rst.getString("address"),
                                        rst.getInt("age"),
                                            rst.getString("mobile"),
                                                rst.getString("job"),
                                                    rst.getString("gender"));
        }
        return Optional.ofNullable(e);
    }

    @Override
    public Optional<List<Employee>> findAll() throws Exception {
        ArrayList<Employee> allemployees = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM Employee");
        while (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
            int age = rst.getInt(4);
            String mobile = rst.getString(5);
            String job = rst.getString(6);
            String gender = rst.getString(7);


            Employee employee = new Employee(id, name, address,age,mobile,job,gender);
            allemployees.add(employee);
        }
        return Optional.ofNullable(allemployees);
    }

    @Override
    public boolean save(Employee employee) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Employee VALUES (?,?,?,?,?,?,?)",
                employee.getEmployee_id(),employee.getEmployee_name(),employee.getAddress(),employee.getAge(),employee.getMobile(),employee.getJob(),employee.getGender()) > 0;
    }

    @Override
    public boolean update(Employee employee) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Employee SET employee_name=?,address=?,age=?,mobile=?,job=?,gender=? WHERE employee_id=?",
                employee.getEmployee_name(),employee.getAddress(),employee.getAge(),employee.getMobile(),employee.getJob(),employee.getGender(),employee.getEmployee_id()) > 0;
    }

    @Override
    public boolean delete(String employee_id) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Employee WHERE employee_id=?", employee_id) > 0;

    }
}
