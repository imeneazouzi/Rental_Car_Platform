import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarService } from '../../services/car.service';
import { OwnerService } from '../../services/owner.service';
import { Car } from '../../models/car';
import { Owner } from '../../models/owner';

@Component({
  selector: 'app-rent-car',
  standalone:false,
  templateUrl: './rent-car.component.html',
  styleUrls: ['./rent-car.component.scss']
})
export class RentCarComponent implements OnInit {
  selectedCar?: Car;
  selectedOwner?: Owner;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService,
    private ownerService: OwnerService
  ) {}

  ngOnInit(): void {
    // Récupère l'ID de la voiture depuis l'URL
    const carId = +this.route.snapshot.paramMap.get('id')!;

    // Charge la voiture correspondante
    this.carService.getCarById(carId).subscribe((car) => {
      this.selectedCar = car;

      // Une fois la voiture chargée, charge son propriétaire
      this.ownerService.getOwners().subscribe((owners) => {
        this.selectedOwner = owners.find(o =>
          o.carsOwned?.some(c => c.id === carId)
        );
      });
    });
  }
}
