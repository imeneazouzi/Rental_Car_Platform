import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarService } from '../../services/car.service';
import { Car } from '../../models/car';

@Component({
  selector: 'app-rent-car',
  standalone: false,
  templateUrl: './rent-car.component.html',
  styleUrls: ['./rent-car.component.scss']
})
export class RentCarComponent implements OnInit {
  carId!: number;
  car: Car = {
    id: 0,
    brand: '',
    model: '',
    year: 0,
    pricePerDay: 0,
    available: false,
    imageUrl: '',
    owner: {
      firstName: '',
      phone: '',
      email: '',
      id: 0,
      lastName: ''
    }
  };


  constructor(private route: ActivatedRoute, private carService: CarService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.carId = +params['id'];
      if (!isNaN(this.carId)) {
        this.fetchCarDetails();
      } else {
        console.error('Invalid car ID in URL');
      }
    });
  }

  fetchCarDetails(): void {
    this.carService.getCarById(this.carId).subscribe(
      car => {
        this.car = car;
        console.log('Car details:', this.car);
        console.log('Owner details:', this.car.owner);
      },
      error => {
        console.error('Error fetching car details:', error);
      }
    );
  }
}
