package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import project.dao.UserDbDao;
import project.dto.UserDto;
import project.model.Role;
import project.model.User;

import java.math.BigDecimal;

@Controller
public class RegistrationController {
    @Autowired
    UserDbDao userDao;

    @GetMapping("/registration")
    public ModelAndView viewRegistration() {
        ModelAndView mv = new ModelAndView("registration");
        mv.addObject("newUser", new UserDto("", "", "", new BigDecimal(0)));
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute UserDto newUser) {
        User user = new User(newUser.name(), newUser.login(), newUser.password(), Role.USER, newUser.startCapital());
        userDao.save(user);
        ModelAndView mv = new ModelAndView("registration");
        mv.addObject("message", "Регистрация прошла успешно!");
        return mv;
    }
}
