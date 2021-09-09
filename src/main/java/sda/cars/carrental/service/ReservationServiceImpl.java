package sda.cars.carrental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.cars.carrental.entity.Reservations;
import sda.cars.carrental.error.CustomException;
import sda.cars.carrental.repository.ReservationsJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationsJpaRepository reservationJpaRepository;

    public ReservationServiceImpl(ReservationsJpaRepository reservationJpaRepository) {
        this.reservationJpaRepository = reservationJpaRepository;
    }

    @Override
    public Reservations findById(long theId) {
        Optional<Reservations> result = reservationJpaRepository.findById(theId);
        Reservations theReservation;
        if (result.isPresent()){
            theReservation = result.get();
        } else {
            throw new CustomException("Reservation with ID=" + theId + " Not found");
        }
        return theReservation;
    }

    @Override
    public List<Reservations> findAll() {
        return reservationJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Reservations theReservation) {
        reservationJpaRepository.save(theReservation);
    }

    @Override
    @Transactional
    public void deleteById(long theId) {
        reservationJpaRepository.deleteById(theId);
    }
}
