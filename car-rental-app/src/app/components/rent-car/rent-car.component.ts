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
  car!: Car;

  constructor(private route: ActivatedRoute, private carService: CarService) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      console.log('Route parameters:', params);
      this.carId = +params['id'];
      console.log('Extracted car ID:', this.carId);
      if (isNaN(this.carId)) {
        console.error('Invalid car ID in URL:', this.carId);
        return;
      }
      this.fetchCarDetails();
    });

  }

  fetchCarDetails(): void {
    this.carService.getCarById(this.carId).subscribe({
      next: (car) => {
        this.car = car;
        console.log('Fetched car details:', car);
      },
      error: (err) => {
        console.error('Error fetching car details:', err);
      }
    });
  }
}
