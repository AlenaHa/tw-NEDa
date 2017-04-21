import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnInit {

  /** latitude and longitude for the map (Nepal coordonates) **/
  lat: number = 28;
  lng: number = 84;

  /** array with different city names from which the user will select **/
  cities = [
    {value: 'kathmandu-0', viewValue: 'Kathmandu'},
    {value: 'bhaktapur-1', viewValue: 'Bhaktapur'},
    {value: 'pyuthan-2', viewValue: 'Pyuthan-3'}
  ]

  constructor() {
  }

  ngOnInit() {
  }

}
