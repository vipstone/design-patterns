package com.design;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created By caiya
 * Date 2019/12/22 10:06
 * Description 数据访问对象模式
 */
public class Lesson30 {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        employeeDAO.addEmployee(Employee.builder().employeeId(1).employeeAge(23).employeeBirth(new Date()).employeeName("caiya").build());
        employeeDAO.addEmployee(Employee.builder().employeeId(2).employeeAge(24).employeeBirth(new Date()).employeeName("laowang").build());
        employeeDAO.addEmployee(Employee.builder().employeeId(3).employeeAge(26).employeeBirth(new Date()).employeeName("lily").build());
        employeeDAO.getEmployees();

        employeeDAO.deleteEmployeeById(1);
        employeeDAO.getEmployees();

        employeeDAO.updateEmployee(Employee.builder().employeeId(3).employeeAge(40).employeeBirth(new Date()).employeeName("修改后的").build());
        employeeDAO.getEmployees();
    }
}
/**
 * 用户实体类
 * @author Administrator
 *
 */
class Employee {
    private Integer employeeId;
    private String employeeName;
    private int employeeAge;
    private Date employeeBirth;
    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public int getEmployeeAge() {
        return employeeAge;
    }
    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }
    public Date getEmployeeBirth() {
        return employeeBirth;
    }
    public void setEmployeeBirth(Date employeeBirth) {
        this.employeeBirth = employeeBirth;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Integer employeeId;
        private String employeeName;
        private int employeeAge;
        private Date employeeBirth;

        public Builder employeeId(Integer employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder employeeAge(int employeeAge) {
            this.employeeAge = employeeAge;
            return this;
        }

        public Builder employeeBirth(Date employeeBirth) {
            this.employeeBirth = employeeBirth;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.setEmployeeAge(employeeAge);
            employee.setEmployeeBirth(employeeBirth);
            employee.setEmployeeId(employeeId);
            employee.setEmployeeName(employeeName);
            return employee;
        }
    }
    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeAge=" + employeeAge + ", employeeBirth=" + employeeBirth
                + "]";
    }

}

/**
 * DAO接口
 * @author Administrator
 *
 */
interface EmployeeDAO {
    /**
     * 新增
     * @param employee
     * @return
     */
    Employee addEmployee(Employee employee);
    /**
     * 查询
     * @return
     */
    List<Employee> getEmployees();
    /**
     * 查询单条
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);
    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteEmployeeById(Integer id);
    /**
     * 修改
     * @param employee
     * @return
     */
    Employee updateEmployee(Employee employee);
}

/**
 * EmployeeDAO的实现类
 * @author Administrator
 *
 */
class EmployeeDAOImpl implements EmployeeDAO{

    private List<Employee> employeeList = new ArrayList<Employee>();

    public Employee addEmployee(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    public List<Employee> getEmployees() {
        employeeList.stream().forEach(System.out::println);
        System.out.println();
        return employeeList;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeList.stream().filter(employee -> employee.getEmployeeId() == id).findFirst().get();
    }

    public boolean deleteEmployeeById(Integer id) {
        Iterator<Employee> iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (employee.getEmployeeId() == id) {
                iterator.remove();
            }
        }
        return true;
    }

    public Employee updateEmployee(Employee employee) {
        for (Employee u : employeeList) {
            if (u.getEmployeeId() == employee.getEmployeeId()) {
                employeeList.set(employeeList.indexOf(u), employee);
            }
        }
        return employee;
    }

}