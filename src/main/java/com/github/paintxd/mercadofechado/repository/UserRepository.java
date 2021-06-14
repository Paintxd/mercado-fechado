package com.github.paintxd.mercadofechado.repository;

import com.github.paintxd.mercadofechado.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
