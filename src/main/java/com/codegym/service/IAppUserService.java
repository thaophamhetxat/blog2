package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.model.Comment;

public interface IAppUserService {
    Iterable<AppUser> findAll();
}
