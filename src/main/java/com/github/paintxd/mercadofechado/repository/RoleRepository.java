package com.github.paintxd.mercadofechado.repository;

import com.github.paintxd.mercadofechado.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
