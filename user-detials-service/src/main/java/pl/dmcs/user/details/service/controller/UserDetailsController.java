package pl.dmcs.user.details.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.user.details.service.model.UserDetails;
import pl.dmcs.user.details.service.service.UserDetailsService;

@RestController
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getUserDetails(@PathVariable int id) {
        return ResponseEntity.ok(userDetailsService.getUserDetails(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addUserDetails(@RequestBody UserDetails userDetails) {
        userDetailsService.addUserDetails(userDetails);
        return ResponseEntity.ok().build();
    }
}
