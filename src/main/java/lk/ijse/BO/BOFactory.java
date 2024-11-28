package lk.ijse.BO;

import lk.ijse.BO.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
       STUDENT,USER,COURSE,REGISTRATION,payment
    }

    //Object creation logic for BO objects
    public SuperBo getBO(BOTypes types){
        switch (types) {

            case STUDENT -> {
                return new StudentBoImpl();
            }

            case USER -> {
                return new UserBoImpl();
            }
            case COURSE ->
            {
                return new CourseBOImpl();
            }
            case REGISTRATION ->
            {
                return new RegistrationBOImpl();
            }
            case payment -> {
                return new PaymentBOImpl();
            }
        }
        return null;
    }
}
