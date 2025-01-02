package dao.login;

import model.Login;

public interface ILoginDAO {
    boolean validate(Login login);
}