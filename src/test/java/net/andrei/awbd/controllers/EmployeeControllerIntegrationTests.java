package net.andrei.awbd.controllers;
import net.andrei.awbd.model.Employee;
import net.andrei.awbd.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;
    @MockBean
    Model model;
    @Test
    public void getByIdMockMvc() throws Exception {
        Long id = Long.valueOf(1);
        Employee employeeTest = new Employee();
        employeeTest.setId(id);
        employeeTest.setFirstName("test");
        when(employeeService.getEmployeeById(id)).thenReturn(employeeTest);
        mockMvc.perform(get("/showFormForUpdate/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update_employee"))
                .andExpect(model().attribute("employee", employeeTest));
    }

}
