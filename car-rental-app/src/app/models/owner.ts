import { Car } from "./car";

export interface Owner {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  carsOwned?: Car[];
}
