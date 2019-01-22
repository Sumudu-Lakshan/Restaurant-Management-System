package lk.ijse.fx.business.custom.impl;

import lk.ijse.fx.business.Converter;
import lk.ijse.fx.business.custom.ManageEmployeeBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.custom.EmployeeDAO;
import lk.ijse.fx.dto.EmployeeDTO;

import java.util.List;

public class ManageEmployeeBOImpl implements ManageEmployeeBO {

    private EmployeeDAO employeeDAO;

    public ManageEmployeeBOImpl() {
        employeeDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    }

    @Override
    public List<EmployeeDTO> getEmployee() throws Exception {
        return employeeDAO.findAll().map(Converter::<EmployeeDTO>getDTOList).get();
    }

    @Override
    public boolean createEmployee(EmployeeDTO dto) throws Exception {
        return employeeDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws Exception {
        return employeeDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteEmployee(String item_id) throws Exception {
        return employeeDAO.delete(item_id);
    }

    @Override
    public EmployeeDTO findEmployee(String item_id) throws Exception {
        return null;
    }
}
