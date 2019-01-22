package lk.ijse.fx.business.custom;

import lk.ijse.fx.business.SuperBO;
import lk.ijse.fx.dto.EmployeeDTO;

import java.util.List;

public interface ManageEmployeeBO extends SuperBO {

    List<EmployeeDTO> getEmployee() throws Exception;

    boolean createEmployee(EmployeeDTO dto) throws Exception;

    boolean updateEmployee(EmployeeDTO dto) throws Exception;

    boolean deleteEmployee(String item_id) throws Exception;

    EmployeeDTO findEmployee(String item_id) throws Exception;
}
