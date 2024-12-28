import { Component, OnInit, inject, signal } from '@angular/core';
import { MaterialService } from '../../services/material.service';
import { MaterialType } from '../../models/material.type';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-material-list',
  standalone: true,
  imports: [NgIf, NgFor, FormsModule],
  templateUrl: './material-list.component.html',
  styleUrls: ['./material-list.component.css']
})
export class MaterialListComponent implements OnInit {
  materialService = inject(MaterialService);
  materials = signal<Array<MaterialType>>([]);
  selectedMaterial: MaterialType | null = null;
  showAddMaterialForm = false;
  showEditMaterialForm = false;
  newMaterial: MaterialType = { id: '', name: '', price: 0, warehouseId: '' };

  ngOnInit(): void {
    this.getMaterials();
  }

  getMaterials(): void {
    this.materialService.getAllMaterials().subscribe(
      data => {
        console.log('Materials:', data);
        this.materials.set(data);
      },
      error => {
        console.error('Error fetching materials:', error);
      }
    );
  }

  deleteMaterial(id: string): void {
    this.materialService.deleteMaterial(id).subscribe(() => {
      this.getMaterials();
    });
  }

  selectMaterial(material: MaterialType): void {
    console.log('Selected Material:', material);
    this.selectedMaterial = { ...material };
  }

  toggleAddMaterialForm(): void {
    this.showAddMaterialForm = !this.showAddMaterialForm;
  }

  toggleEditMaterialForm(material: MaterialType): void {
    this.selectedMaterial = { ...material };
    this.showEditMaterialForm = !this.showEditMaterialForm;
  }

  addMaterial(): void {
    console.log('Adding Material:', this.newMaterial);
    this.materialService.createMaterial(this.newMaterial).subscribe(() => {
      this.getMaterials();
      this.showAddMaterialForm = false;
      this.newMaterial = { id: '', name: '', price: 0, warehouseId: '' };
    }, error => {
      console.error('Error adding material:', error);
    });
  }

  updateMaterial(): void {
    if (this.selectedMaterial) {
      this.materialService.updateMaterial(this.selectedMaterial.id, this.selectedMaterial).subscribe(() => {
        this.getMaterials();
        this.showEditMaterialForm = false;
      });
    }
  }

  showDetails(material: MaterialType): void {
    console.log('Fetching Details for Material ID:', material.id);
    this.materialService.getMaterialById(material.id).subscribe(
      data => {
        console.log('Material Details:', data);
        this.selectedMaterial = data;
      },
      error => {
        console.error('Error fetching material details:', error);
      }
    );
  }
}
