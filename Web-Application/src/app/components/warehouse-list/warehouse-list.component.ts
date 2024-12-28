import { Component, OnInit, inject, signal } from '@angular/core';
import { WarehouseService } from '../../services/warehouse.service';
import { WarehouseType } from '../../models/warehouse.type';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialService } from '../../services/material.service';
import { MaterialType } from '../../models/material.type';

@Component({
  selector: 'app-warehouse-list',
  standalone: true,
  imports: [NgIf, NgFor, FormsModule],
  templateUrl: './warehouse-list.component.html',
  styleUrls: ['./warehouse-list.component.css']
})
export class WarehouseListComponent implements OnInit {
  warehouseService = inject(WarehouseService);
  warehouses = signal<Array<WarehouseType>>([]);
  selectedWarehouse: WarehouseType | null = null;
  showAddWarehouseForm = false;
  showEditWarehouseForm = false;
  newWarehouse: WarehouseType = { id: '', name: '', location: '' };
  materialService = inject(MaterialService);
  materials = signal<Array<MaterialType>>([]);

  ngOnInit(): void {
    this.getWarehouses();
  }

  getWarehouses(): void {
    this.warehouseService.getAllWarehouses().subscribe(
      data => {
        console.log('Warehouses:', data);
        this.warehouses.set(data);
      },
      error => {
        console.error('Error fetching warehouses:', error);
      }
    );
  }

  deleteWarehouse(id: string): void {
    this.warehouseService.deleteWarehouse(id).subscribe(() => {
      this.getWarehouses();
    });
  }

  selectWarehouse(warehouse: WarehouseType): void {
    console.log('Selected Warehouse:', warehouse);
    this.selectedWarehouse = { ...warehouse };
  }

  toggleAddWarehouseForm(): void {
    this.showAddWarehouseForm = !this.showAddWarehouseForm;
  }

  toggleEditWarehouseForm(warehouse: WarehouseType): void {
    this.selectedWarehouse = { ...warehouse };
    this.showEditWarehouseForm = !this.showEditWarehouseForm;
  }

  addWarehouse(): void {
    console.log('Adding Warehouse:', this.newWarehouse);
    this.warehouseService.createWarehouse(this.newWarehouse).subscribe(() => {
      this.getWarehouses();
      this.showAddWarehouseForm = false;
      this.newWarehouse = { id: '', name: '', location: '' };
    }, error => {
      console.error('Error adding warehouse:', error);
    });
  }

  updateWarehouse(): void {
    if (this.selectedWarehouse) {
      this.warehouseService.updateWarehouse(this.selectedWarehouse.id, this.selectedWarehouse).subscribe(() => {
        this.getWarehouses();
        this.showEditWarehouseForm = false;
      });
    }
  }

  showDetails(warehouse: WarehouseType): void {
    console.log('Fetching Details for Warehouse ID:', warehouse.id);
    this.warehouseService.getWarehouseById(warehouse.id).subscribe(
      data => {
        console.log('Warehouse Details:', data);
        this.selectedWarehouse = data;
        this.getMaterialsByWarehouseId(warehouse.id);
      },
      error => {
        console.error('Error fetching warehouse details:', error);
      }
    );
  }

  getMaterialsByWarehouseId(warehouseId: string): void {
    this.materialService.getMaterialsByWarehouseId(warehouseId).subscribe(
      data => {
        console.log('Materials for Warehouse:', data);
        this.materials.set(data);
      },
      error => {
        console.error('Error fetching materials for warehouse:', error);
      }
    );
  }

}
