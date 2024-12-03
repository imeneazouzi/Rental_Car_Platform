import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarListComponent } from './components/car-list/car-list.component';
import { RentCarComponent } from './components/rent-car/rent-car.component';
import { DetailsComponent } from './details/details.component';

const routes: Routes = [
  { path: '', component: CarListComponent },
  { path: 'rent/:id', component: RentCarComponent },
  { path: 'details', component: DetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
