package com.qdbp.service;

import com.qdbp.entity.AppUser;

public interface AppUserService {

    String registerUser(AppUser appUser);

    String setEmail(AppUser appUser, String email);
}
