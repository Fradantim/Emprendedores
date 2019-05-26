package com.tmi.emprendedores.service;

public interface SecurityService {
    String findLoggedInNick();

    void autoLogin(String nick, String password);
}