package lk.ijse.fx.business;

import lk.ijse.fx.business.custom.impl.*;

public class BOFactory {

    public enum BOTypes{
        MANAGE_MENU, MANAGE_EMPLOYEE, MANAGE_RESTAURATNTTABLE, MANAGE_ORDERS, MANAGE_PAYMENT
    }

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType){
        switch (boType){
            case MANAGE_MENU:
                return (T) new ManageMenuBOImpl();
            case MANAGE_EMPLOYEE:
                return (T) new ManageEmployeeBOImpl();
            case MANAGE_RESTAURATNTTABLE:
                return (T) new ManageRestaurantTableBOImpl();
            case MANAGE_ORDERS:
                return (T) new ManageOrdersBOImpl();
            case MANAGE_PAYMENT:
                return (T) new ManagePaymentBOImpl();

            default:
                return null;
        }
    }

}
