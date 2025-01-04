package com.example.hotels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotels.Model.Contact;
import com.example.hotels.Service.EmailService;


@Controller
public class EmailController {
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/submitContactForm")
    public String submitContactForm(@RequestParam String name, 
                                    @RequestParam String email, 
                                    @RequestParam String number,
                                    @RequestParam String message, 
                                    Model model){
                                        Contact contact=new Contact();
                                        contact.setName(name);
                                        contact.setEmail(email);
                                        contact.setNumber(number);
                                        contact.setMessage(message);
                                    emailService.sendContactFormEmail(contact);
                                    model.addAttribute("successMessage", "Your message has been sent successfully!");
                                    return "contact";         
                                }
}
