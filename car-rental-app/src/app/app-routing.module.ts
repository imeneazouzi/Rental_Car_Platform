import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarListComponent } from './components/car-list/car-list.component';
import { RentCarComponent } from './components/rent-car/rent-car.component';

const routes: Routes = [
  { path: '', component: CarListComponent },
  { path: 'rent/:id', component: RentCarComponent } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
