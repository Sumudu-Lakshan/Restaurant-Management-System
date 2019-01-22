package lk.ijse.fx.business.custom;

import lk.ijse.fx.business.SuperBO;
import lk.ijse.fx.dto.PaymentDTO;
import lk.ijse.fx.entity.Payment;

import java.util.List;

public interface ManagePaymentBO extends SuperBO {

    List<PaymentDTO> getPaymentList() throws Exception;

    boolean createPayment(PaymentDTO dto) throws Exception;

    String generatePaymentId() throws Exception;

}
