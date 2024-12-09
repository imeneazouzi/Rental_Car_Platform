import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarService } from '../../services/car.service';
import { OwnerService } from '../../services/owner.service';
import { Car } from '../../models/car';
import { Owner } from '../../models/owner';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-rent-car',
  standalone:false,
  templateUrl: './rent-car.component.html',
  styleUrls: ['./rent-car.component.scss']
})
export class RentCarComponent implements OnInit, OnDestroy {
  selectedCar?: Car;
  selectedOwner?: Owner;
  otherCars: Car[] = [];
  private routeSub: Subscription = new Subscription();

  constructor(
    private carService: CarService,
    private ownerService: OwnerService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Observer les changements de route
    this.routeSub = this.route.params.subscribe(params => {
      const carId = +params['id']; // Récupérer l'ID de la voiture depuis l'URL
      this.loadCarDetails(carId);
      this.loadOtherCars(carId); // Charger les autres voitures disponibles
    });
  }

  ngOnDestroy(): void {
    // Se désabonner lors de la destruction du composant
    this.routeSub.unsubscribe();
  }

  loadCarDetails(carId: number): void {
    this.carService.getCarById(carId).subscribe((car: Car) => {
      this.selectedCar = car;
      this.loadOwner(car.id);
    });
  }

  loadOwner(carId: number): void {
    this.ownerService.getOwners().subscribe((owners: Owner[]) => {
      const owner = owners.find(o => o.carsOwned?.some(c => c.id === carId));
      if (owner) {
        this.selectedOwner = owner;
      }
    });
  }

  loadOtherCars(excludeCarId: number): void {
    this.carService.getCarsList().subscribe((cars: Car[]) => {
      this.otherCars = cars.filter(car => car.id !== excludeCarId);
    });
  }
}
