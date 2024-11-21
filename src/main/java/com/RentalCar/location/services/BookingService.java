package com.RentalCar.location.services;

import com.RentalCar.location.model.Booking;
import com.RentalCar.location.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByClientId(Long clientId) {
        return bookingRepository.findByClientId(clientId);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
        existingBooking.setStartDate(bookingDetails.getStartDate());
        existingBooking.setEndDate(bookingDetails.getEndDate());
        existingBooking.setBookingStatus(bookingDetails.getBookingStatus());
        return bookingRepository.save(existingBooking);
    }

    public void deleteBookingById(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Booking not found with id " + id);
        }
    }
}
