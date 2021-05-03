package com.github.paintxd.mercadofechado.repository;

import com.github.paintxd.mercadofechado.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
