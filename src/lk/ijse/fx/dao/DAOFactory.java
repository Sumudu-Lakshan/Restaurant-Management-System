package lk.ijse.fx.dao;

import lk.ijse.fx.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public enum DAOTypes{
            EMPLOYEE,MENU,ORDER,ORDERDETAIL,PAYMENT,RESTAURANTTABLE,QUERY

    }

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        if(daoFactory == null){
           return daoFactory = new DAOFactory();
        }
        return daoFactory;

    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType){
        switch (daoType){

            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();

            case MENU:
                return (T) new MenuDAOImpl();

            case ORDER:
                return (T) new OrderDAOImpl();

            case ORDERDETAIL:
                return (T) new OrderDetailDAOImpl();

            case PAYMENT:
                return (T) new PaymentDAOImpl();

            case QUERY:
                return (T) new QueryDAOImpl();

            case RESTAURANTTABLE:
                return (T) new RestaurantTableDAOImpl();

            default:
                return null;
        }
    }



}
