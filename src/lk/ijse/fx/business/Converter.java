package lk.ijse.fx.business;

import lk.ijse.fx.dto.*;
import lk.ijse.fx.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static <T extends SuperDTO> T getDTO(SuperEntity entity) {
        if (entity instanceof Menu) {
            Menu m = (Menu) entity;
            return (T) new MenuDTO(m.getItem_code(),m.getDescription(),m.getUnit_price(),m.getMeal_type());

        }else if (entity instanceof Employee) {
            Employee emp = (Employee) entity;
            return (T) new EmployeeDTO(emp.getEmployee_id(),emp.getEmployee_name(),
                    emp.getAddress(),emp.getAge(),emp.getMobile(),emp.getJob(),emp.getGender());

        }else if (entity instanceof RestaurantTable) {
            RestaurantTable tbl = (RestaurantTable) entity;
            return (T) new RestaurantTableDTO(tbl.getTable_number(),tbl.getTable_name(),
                    tbl.getSeats(),tbl.getStatus());

        }else if (entity instanceof Payment) {
            Payment payment = (Payment) entity;
            return (T) new PaymentDTO(payment.getPayment_id(),payment.getOrder_id(),payment.getPayment_method(),payment.getTotal());

        }else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }

    public static <T extends SuperEntity> T getEntity(SuperDTO dto) {
        if (dto instanceof MenuDTO) {
            MenuDTO m = (MenuDTO) dto;
            return (T) new Menu(m.getItem_code(),m.getDescription(),m.getUnit_price(),m.getMeal_type());

        }else if(dto instanceof EmployeeDTO){
            EmployeeDTO emp = (EmployeeDTO) dto;
            return (T) new Employee(emp.getEmployee_id(),emp.getEmployee_id(),
                    emp.getAddress(),emp.getAge(),emp.getMobile(),emp.getJob(),emp.getGender());

        }else if(dto instanceof RestaurantTableDTO){
            RestaurantTableDTO tbl = (RestaurantTableDTO) dto;
            return (T) new RestaurantTable(tbl.getTable_number(),tbl.getTable_name(),tbl.getSeats(),tbl.getStatus());

        }else if(dto instanceof PaymentDTO){
            PaymentDTO payment = (PaymentDTO) dto;
            return (T) new Payment(payment.getPayment_id(),payment.getOrder_id(),payment.getPayment_method(),payment.getTotal());

        }
        else {
            throw new RuntimeException("This DTO can't be converted to an entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }


    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }

    //====================================================================================

//    public static <T extends SuperDTO> T getDTO(CustomEntity entity, Class<T> dtoClass) {
//        if (dtoClass == OrderDTO.class) {
//            return (T) new OrderDTO(entity.getOrder_id(), entity.getDate(),
//                    entity.getTable_number(), entity.getEmployee_id(), entity.getTotal());
//        } else if (dtoClass == OrderDetailDTO.class) {
//            return (T) new OrderDetailDTO(entity.getItemCode(), entity.getDescription(), entity.getQty(), entity.getUnitPrice());
//        } else {
//            throw new RuntimeException("Not Supported DTO");
//        }
//    }
//
//    public static <T extends SuperDTO> List<T> getDTOList(List<CustomEntity> list, Class<T> dtoClass) {
//        return list.stream().map(c -> getDTO(c, dtoClass)).collect(Collectors.toList());
//    }

}
