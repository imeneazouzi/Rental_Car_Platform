import { Component, OnInit } from '@angular/core';
import { Car } from './../../models/car';
import { Owner } from './../../models/owner';
import { CarService } from '../../services/car.service';
import { OwnerService } from '../../services/owner.service';

@Component({
  selector: 'app-car-list',
  standalone: false,
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.scss'] // Correction : styleUrls est un tableau
})
export class CarListComponent implements OnInit {
  cars: Car[] = [];
  owners: Owner[] = [];
  selectedCar?: Car;
  selectedOwner?: Owner; 

  constructor(
    private carService: CarService,
    private ownerService: OwnerService
  ) {}

  ngOnInit(): void {
    this.carService.getCarsList().subscribe((data: Car[]) => {
      this.cars = data;
    });

    this.ownerService.getOwners().subscribe((data: Owner[]) => {
      this.owners = data;
    });
  }


  onRentCar(car: Car): void {

    const owner = this.owners.find(o => o.carsOwned?.some(c => c.id === car.id));
    if (owner) {
      this.selectedCar = car;
      this.selectedOwner = owner;
    } else {
      console.error('Aucun propriétaire trouvé pour cette voiture.');
    }
  }
}
