package lk.ijse.fx.business.custom.impl;

import lk.ijse.fx.business.custom.ManageOrdersBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.custom.*;
import lk.ijse.fx.db.DBConnection;
import lk.ijse.fx.dto.OrderDTO;
import lk.ijse.fx.dto.OrderFullDetatailDTO;
import lk.ijse.fx.entity.CustomEntity;
import lk.ijse.fx.entity.Order;
import lk.ijse.fx.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageOrdersBOImpl implements ManageOrdersBO {

    private OrderDAO orderDAO;
    private RestaurantTableDAO tableDAO;
    private EmployeeDAO waiterDao;
    private MenuDAO menuItemDao;
    private OrderDetailDAO orderDetailDAO;
    private PaymentDAO paymentDAO;
    private QueryDAO queryDAO;

    public ManageOrdersBOImpl() {
        orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        tableDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESTAURANTTABLE);
        waiterDao = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
        menuItemDao = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MENU);
        paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    }


    @Override
    public List<OrderFullDetatailDTO> getOrdersWithEmployeeAndTableAndTotals() throws Exception {
        List<CustomEntity> fullyDetailOfOrder = queryDAO.findFullyDetailOfOrder();
        List<OrderFullDetatailDTO> fulldetail = new ArrayList<>();
        for (CustomEntity customEntity : fullyDetailOfOrder) {
            fulldetail.add(new OrderFullDetatailDTO(customEntity.getOrder_id(),
                                                customEntity.getDate(),
                                                customEntity.getDining(),
                                                customEntity.getTable_number(),
                                                customEntity.getEmployee_id(),
                                                customEntity.getSubTotal()));
        }
        return fulldetail;
    }

    @Override
    public List<OrderDTO> findOrder(String orderid) throws Exception {
        List<CustomEntity> orderWithItemDetails = queryDAO.findOrderWithItemDetails(orderid);
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (CustomEntity order : orderWithItemDetails) {
            orderDTOS.add(new OrderDTO(order.getItem_id(),order.getDescription(),order.getQuantity(),order.getUnit_price()));
        }
        return orderDTOS;
    }

    @Override
    public String generateOrderID() throws Exception {
        return orderDAO.count() + 1 + "";
    }

//    @Override
//    public String generatePaymentID() throws Exception {
//        return paymentDAO.count() + 1 + "";
//    }

    @Override
    public void createOrder(OrderDTO dto) throws Exception {

        DBConnection.getInstance().getConnection().setAutoCommit(false);

    try {


        boolean result = orderDAO.save(new Order(dto.getOrder_id(), dto.getDate(), dto.getTable_number(),
                dto.getEmployee_id(), dto.getDining()));

        if (!result) {
            return;
        }
        for (OrderDTO detail : dto.getOrderDetails()) {
            orderDetailDAO.save(new OrderDetail(dto.getOrder_id(), detail.getItemcode(), detail.getQuantity(),
                    detail.getUnitPrice()));
        }

        if (!result) {
            DBConnection.getInstance().getConnection().rollback();
            return;
        }
    }catch (Exception ex){
        DBConnection.getInstance().getConnection().rollback();
        ex.printStackTrace();
    }
    }

}
