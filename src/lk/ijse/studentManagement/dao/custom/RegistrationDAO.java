package lk.ijse.studentManagement.dao.custom;

import lk.ijse.studentManagement.dao.SuperDAO;
import lk.ijse.studentManagement.entity.Registration;

public interface RegistrationDAO extends SuperDAO<Registration, String> {
    public String getLastRegNo () throws Exception;
}
