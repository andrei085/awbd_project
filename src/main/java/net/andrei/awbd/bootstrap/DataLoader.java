
package net.andrei.awbd.bootstrap;

import lombok.AllArgsConstructor;
import net.andrei.awbd.model.City;
import net.andrei.awbd.model.Department;
import net.andrei.awbd.model.Role;
import net.andrei.awbd.model.User;
import net.andrei.awbd.repository.CityRepository;
import net.andrei.awbd.repository.DepartmentRepository;
import net.andrei.awbd.repository.RoleRepository;
import net.andrei.awbd.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Profile("mysql")
public class DataLoader implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private PasswordEncoder passwordEncoder;
    private CityRepository cityRepository;


    private void loadUserData() {
        if (userRepository.count() == 0){
            Role adminRole = roleRepository.save(Role.builder().name("ROLE_ADMIN").build());
            Role guestRole = roleRepository.save(Role.builder().name("ROLE_GUEST").build());

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("12345"))
                    .role(adminRole)
                    .build();

            User guest = User.builder()
                    .username("guest")
                    .password(passwordEncoder.encode("12345"))
                    .role(guestRole)
                    .build();

            userRepository.save(admin);
            userRepository.save(guest);

        }
    }

    private void loadCities(){
        if (cityRepository.count() == 0) {
            cityRepository.save(City.builder().name("Constanta").build());
            cityRepository.save(City.builder().name("Bucuresti").build());
            cityRepository.save(City.builder().name("Iasi").build());
            cityRepository.save(City.builder().name("Sibiu").build());
            cityRepository.save(City.builder().name("Brasov").build());
        }
    }

    private void loadDepartments(){
        if (departmentRepository.count() == 0) {
            departmentRepository.save(Department.builder().name("HR").build());
            departmentRepository.save(Department.builder().name("IT").build());
            departmentRepository.save(Department.builder().name("Finance").build());
            departmentRepository.save(Department.builder().name("Sales").build());
        }
    }


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadCities();
        loadDepartments();
    }
}
