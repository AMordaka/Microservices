package pl.dmcs.user.details.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.user.details.service.exception.UserDetailsAlreadyExistException;
import pl.dmcs.user.details.service.exception.UserDetailsNotFoundException;
import pl.dmcs.user.details.service.model.UserDetails;
import pl.dmcs.user.details.service.model.dto.AccountNumberDto;
import pl.dmcs.user.details.service.service.UserDetailsService;

@RestController
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserDetails(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userDetailsService.getUserDetails(id));
        } catch (UserDetailsNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity addUserDetails(@RequestBody UserDetails userDetails) {
        try {
            userDetailsService.addUserDetails(userDetails);
            return ResponseEntity.ok().build();
        } catch (UserDetailsAlreadyExistException | UserDetailsNotFoundException e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}/updateAccountNumber")
    public ResponseEntity updateAccountNumber(@PathVariable int id, @RequestBody AccountNumberDto accountNumberDto) {
        try {
            userDetailsService.updateAccountNumber(id, accountNumberDto);
            return ResponseEntity.ok().build();
        } catch (UserDetailsNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
