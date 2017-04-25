import { Component } from '@angular/core';

@Component({
  selector: 'supplies-component',
  templateUrl: './supplies.component.html',
  styleUrls: ['./supplies.component.scss']
})

export class SuppliesComponent{
  selectedValue: string;

  locations = [
    {value: 'location-0', viewValue: 'Kathmandu'},
    {value: 'location-1', viewValue: 'Bhaktapur'},
    {value: 'location-2', viewValue: 'Pyuthan'}
  ];

  supplies = [
    {value: 'supply-0', viewValue: 'Food'},
    {value: 'supply-1', viewValue: 'Wather'},
    {value: 'supply-2', viewValue: 'Clothes'}
  ];

  suppliesType = [
    {value: 'sType-0', viewValue: 'Shelter'},
    {value: 'sType-1', viewValue: 'Food'},
    {value: 'sType-2', viewValue: 'Medicine'}
  ];
}
