import { Component,OnInit } from '@angular/core';
import {Car} from './../../models/car'
import { CarService } from '../../services/car.service';
@Component({
  selector: 'app-car-list',
  standalone: false,

  templateUrl: './car-list.component.html',
  styleUrl: './car-list.component.scss'
})
export class CarListComponent implements OnInit {
  cars: Car[] = [];

  constructor(private carService: CarService) {}

  ngOnInit(): void {
    this.carService.getCarsList().subscribe((data: Car[]) => {
      this.cars = data;
    });
  }
}

