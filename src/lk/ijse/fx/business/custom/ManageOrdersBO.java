package lk.ijse.fx.business.custom;

import lk.ijse.fx.business.SuperBO;
import lk.ijse.fx.dto.OrderDTO;
import lk.ijse.fx.dto.OrderFullDetatailDTO;
import lk.ijse.fx.dto.PaymentDTO;

import java.util.List;

public interface ManageOrdersBO extends SuperBO {

   List<OrderFullDetatailDTO> getOrdersWithEmployeeAndTableAndTotals() throws Exception;
//
//    List<OrderFullDTO>getOrders()throws Exception;

//
    String generateOrderID() throws Exception;

   // String generatePaymentID() throws Exception;
//
    void createOrder(OrderDTO dto) throws Exception;

//
      List<OrderDTO> findOrder(String orderid) throws Exception;



}
