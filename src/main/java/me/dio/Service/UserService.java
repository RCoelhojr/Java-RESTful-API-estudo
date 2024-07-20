package me.dio.Service;

import me.dio.domain.model.User;

public interface UserService {

    Object create(User userToCreate);

    Object findById(Long id);

}
