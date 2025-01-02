package service.login;

import dao.login.ILoginDAO;
import dao.login.LoginDAOImpl;
import model.Login;

public class LoginServiceImpl implements ILoginService {
    private ILoginDAO loginDAO = new LoginDAOImpl();

    @Override
    public boolean validate(Login login) {
        return loginDAO.validate(login);
    }
}