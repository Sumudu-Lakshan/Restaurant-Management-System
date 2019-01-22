package lk.ijse.fx.business.custom.impl;

import lk.ijse.fx.business.Converter;
import lk.ijse.fx.business.custom.ManagePaymentBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.custom.PaymentDAO;
import lk.ijse.fx.dto.PaymentDTO;

import java.util.List;

public class ManagePaymentBOImpl implements ManagePaymentBO {

    private PaymentDAO paymentDAO;

    public ManagePaymentBOImpl() {
        paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    }

    @Override
    public List<PaymentDTO> getPaymentList() throws Exception {
        return paymentDAO.findAll().map(Converter::<PaymentDTO>getDTOList).get();
    }

    @Override
    public boolean createPayment(PaymentDTO dto) throws Exception {
       return paymentDAO.save(Converter.getEntity(dto));
    }

    @Override
    public String generatePaymentId() throws Exception {
        return paymentDAO.count() + 1 + "";
    }


}
