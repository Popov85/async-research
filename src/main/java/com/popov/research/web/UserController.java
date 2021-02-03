package com.popov.research.web;

import com.popov.research.domain.Achievement;
import com.popov.research.domain.Department;
import com.popov.research.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/default")
    public User getUserDefault() throws Exception {
        Thread.sleep(2500);
        log.debug("Calculated first user");
        return new User("Andrey");
    }

    @GetMapping("/special")
    public User getUserSpecial() throws Exception {
        Thread.sleep(2500);
        log.debug("Calculated second user");
        return new User("Nikola");
    }

    @GetMapping("/extreme")
    public User getUserExtreme() throws Exception {
        Thread.sleep(2500);
        log.debug("Calculated third user");
        return new User("Valentine");
    }

    //-------------------------------------------------------

    @GetMapping("/department")
    public Department getDepartment() throws Exception {
        Thread.sleep(2500);
        log.debug("Calculated department");
        return new Department("IT");
    }

    @GetMapping("/achievement")
    public Achievement getAchievement() throws Exception {
        Thread.sleep(2500);
        log.debug("Calculated achievements");
        return new Achievement("Award Best User");
    }
}
