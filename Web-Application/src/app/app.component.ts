import { Component } from '@angular/core';
import { WarehouseListComponent } from './components/warehouse-list/warehouse-list.component';
import { MaterialListComponent } from './components/material-list/material-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [WarehouseListComponent, MaterialListComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Warehouse Material Management';
}
