package lk.ijse.studentManagement.bo.custom;

import lk.ijse.studentManagement.bo.SuperBO;
import lk.ijse.studentManagement.dto.RegistrationDTO;

import java.util.List;

public interface RegistrationBO extends SuperBO {
    public boolean add (RegistrationDTO r) throws Exception;
    public String getNewRegNo () throws Exception;
    public List<RegistrationDTO> getAll() throws Exception;
}
