package lk.ijse.BO.impl;

import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.StudentDao;


public class RegistrationBOImpl implements RegistrationBO {


    StudentDao studentDao = (StudentDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.STUDENT);



}
