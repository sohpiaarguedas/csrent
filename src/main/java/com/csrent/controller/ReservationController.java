package com.csrent.controller;

import com.csrent.dto.ReservationDto;
import com.csrent.model.Reservation;
import com.csrent.model.User;
import com.csrent.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/reservation")

public class ReservationController {
    @Autowired

    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAll(){
        return ResponseEntity.ok(reservationService.getAll());

    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto reservationDto){
        return ResponseEntity.ok(reservationService.add(reservationDto));
    }
/*
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody Reservation reservation) {
        User updatedUser = reservationService.updateReservation;
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<User> partialUpdateUser(@RequestBody Reservation reservation) {
        User updatedUser = userService.edit(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 */
}
