package net.andrei.awbd.repository;

import net.andrei.awbd.model.Projects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("mysql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Test
    public void AllProjects(){
        List<Projects> projectsList = projectsRepository.findAll();
        assertTrue(projectsList.size() >= 5);
    }

    @Test
    public void ProjectExists(){
        boolean result = projectsRepository.existsById(Long.valueOf(1));
        assertTrue(result);
    }

}
