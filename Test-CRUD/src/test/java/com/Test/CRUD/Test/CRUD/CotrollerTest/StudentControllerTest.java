package com.Test.CRUD.Test.CRUD.CotrollerTest;

import com.Test.CRUD.Test.CRUD.Controllers.StudentController;
import com.Test.CRUD.Test.CRUD.Entities.Student;
import com.Test.CRUD.Test.CRUD.Services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void contextLoads() {
        assertThat(studentController).isNotNull();
    }
    @Test
    @Order(1)
    void createStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Ivan");
        student.setSurname("Piccioni");
        student.setWorking(true);

        String studentJSON = objectMapper.writeValueAsString(student);

        MvcResult resultActions = this.mockMvc.perform(post("/student/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(2)
    void updateStudenteId() throws Exception {
        Long studenteId = 1L;
        Student updateStudente = new Student(studenteId, "Francesco", "Totti", false);
        String studentJSON = objectMapper.writeValueAsString(updateStudente);

        MvcResult resultUpdate = this.mockMvc.perform(MockMvcRequestBuilders.put("/student/update/{id}", studenteId)
                        .contentType(MediaType.APPLICATION_JSON).content(studentJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = resultUpdate.getResponse().getContentAsString();
        Assertions.assertNotNull(content);

    }

    @Test
    @Order(3)
    void getAllStudent() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/student/returnAll"))
                .andDo(print()).andReturn();

        List<Student> userFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(),
                List.class);
        assertThat(userFromResponseList.size()).isNotZero();
    }
    @Test
    @Order(4)
    void getStudenteId() throws Exception {
        Long studenteId = 1L;

        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/student/return/{id}", studenteId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(studenteId)).andReturn();
    }


    @Test
    @Order(5)
    void updateIsWorking() throws Exception {
        Long studenteId = 1L;
        boolean isWorking = false;

        mockMvc.perform(MockMvcRequestBuilders.patch("/student/setStato/{id}", studenteId)
                        .param("working", String.valueOf(isWorking)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(6)
    void deleteStudente() throws Exception {
        Long studentId = 1L;

        MvcResult result = mockMvc.perform(delete("/student/delete/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
}

