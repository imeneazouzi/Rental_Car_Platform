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
  filteredCars: Car[] = [];
  brands: string[] = [];
  models: string[] = [];
  years: number[] = [];
  selectedBrand: string = '';
  selectedModel: string = '';
  selectedYear: number | null = null;
  selectedAvailable: string = '';

  constructor(
    private carService: CarService,
    private ownerService: OwnerService
  ) {}

  ngOnInit(): void {
    this.carService.getCarsList().subscribe((data: Car[]) => {
      this.cars = data;
      this.filteredCars = this.cars;
      this.brands = Array.from(new Set(data.map(car => car.brand)));
      this.models = Array.from(new Set(data.map(car => car.model)));
      this.years = Array.from(new Set(data.map(car => car.year)));
    });

    this.ownerService.getOwners().subscribe((data: Owner[]) => {
      this.owners = data;
    });
  }
  filterCars(): void {
    this.filteredCars = this.cars.filter(car => {
      return (
        (this.selectedBrand ? car.brand === this.selectedBrand : true) &&
        (this.selectedModel ? car.model === this.selectedModel : true) &&
        (this.selectedYear ? car.year === this.selectedYear : true) &&
        (this.selectedAvailable ? car.available.toString() === this.selectedAvailable : true)
      );
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
