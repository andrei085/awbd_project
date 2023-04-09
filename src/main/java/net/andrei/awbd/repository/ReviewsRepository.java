package net.andrei.awbd.repository;

import net.andrei.awbd.model.Projects;
import net.andrei.awbd.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long>{

}
