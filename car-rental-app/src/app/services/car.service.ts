import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Car} from './../models/car'

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private apiUrl = 'http://localhost:8080/cars';

  constructor(private http:HttpClient) { }
  getCarsList():Observable<Car[]>{
    return this.http.get<Car[]>(this.apiUrl);

  }
  addCar(car: Car): Observable<Car> {
    return this.http.post<Car>(this.apiUrl, car);
  }
  getCarById(id: number): Observable<Car> {
    return this.http.get<Car>(`http://localhost:8080/cars/${id}`);
  }

}
