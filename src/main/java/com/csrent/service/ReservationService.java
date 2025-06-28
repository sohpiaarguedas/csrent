package com.csrent.service;

import com.csrent.dto.ReservationDto;
import com.csrent.model.Reservation;
import com.csrent.model.Space;
import com.csrent.model.User;
import com.csrent.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
       private UserService userService;
      private SpaceService spaceService;


    public ReservationService(ReservationRepository reservationRepository, UserService userService, SpaceService spaceService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.spaceService = spaceService;
    }

    public List<Reservation> getAll(){

        return reservationRepository.findAll();
    }

    public Reservation add(ReservationDto reservationDto){
        User user= userService.getUserByEmail(reservationDto.getEmailUser());
        Space space = spaceService.getSpace(reservationDto.getIdSpace());
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSpace(space);
        reservation.setDateReservation(reservationDto.getDate());
        reservation.setStatus(reservationDto.getStatus());

    return reservationRepository.save(reservation);
    }
  // public User updateReservation(Reservation reservation) {}


}//fin del class
