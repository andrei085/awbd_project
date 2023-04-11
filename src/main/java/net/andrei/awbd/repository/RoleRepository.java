package net.andrei.awbd.repository;

import net.andrei.awbd.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}