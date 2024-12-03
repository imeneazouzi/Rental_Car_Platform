import { Component } from '@angular/core';

@Component({
  selector: 'app-details',
  standalone: false,

  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent {
  constructor() { }

  ngOnInit(): void {
    // Cette méthode sera appelée lorsque le composant sera initialisé
  }

}
