import { Owner } from './owner';
export interface Car {
  id: number;
  brand: string;
  model: string;
  year:number;
  pricePerDay: number;
  available: boolean;
  imageUrl: string;
  owner: Owner; 
}
