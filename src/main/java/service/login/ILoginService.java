package service.login;

import model.Login;

public interface ILoginService {
    boolean validate(Login login);
}