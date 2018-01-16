package goit.finalproject.rest;

import goit.finalproject.rest.Service.EmployeeService;
import goit.finalproject.rest.email.EmailService;
import goit.finalproject.rest.email.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Mail mail = new Mail();

        mail.setFrom("noreply@goit.com");
        mail.setTo("d.manzhula@gmail.com");
        mail.setSubject("salary month report");
        mail.setContent("Hello world!");

        emailService.sendSimpleMessage(mail);
    }
}
