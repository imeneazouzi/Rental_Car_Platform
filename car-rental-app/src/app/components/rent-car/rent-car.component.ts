import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarService } from '../../services/car.service';
import { Car } from '../../models/car';

@Component({
  selector: 'app-rent-car',
  standalone: false,

  templateUrl: './rent-car.component.html',
  styleUrl: './rent-car.component.scss'
})
export class RentCarComponent implements OnInit {
  car!: Car;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService
  ) {}

  ngOnInit(): void {
    const carId = this.route.snapshot.params['id'];
    this.carService.getCarById(carId).subscribe((data: Car) => {
      this.car = data;
    });
  }
}

