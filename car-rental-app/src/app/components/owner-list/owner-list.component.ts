import { Component ,OnInit} from '@angular/core';
import { OwnerService } from '../../services/owner.service';
import { Owner } from '../../models/owner';
@Component({
  selector: 'app-owner-list',
  standalone: false,

  templateUrl: './owner-list.component.html',
  styleUrl: './owner-list.component.scss'
})
export class OwnerListComponent implements OnInit {
  owners: Owner[] = [];

  constructor(private ownerService: OwnerService) {}

  ngOnInit(): void {
    this.ownerService.getOwners().subscribe({
      next: (data: Owner[]) => {
        console.log('Fetched owners:', data);
        this.owners = data;
      },
      error: (error) => {
        console.error('Error fetching owners:', error);
      }
    });
  }
}
