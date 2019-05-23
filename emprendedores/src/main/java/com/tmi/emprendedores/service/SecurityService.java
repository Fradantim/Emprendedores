package com.tmi.emprendedores.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}