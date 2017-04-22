import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnInit {

  /** latitude and longitude for the map (Nepal coordonates) **/
  public lat: number = 28;
  public lng: number = 84;

  /** array with different city names from which the user will select **/
  cities = [
    {value: 'kathmandu-0', viewValue: 'Kathmandu'},
    {value: 'bhaktapur-1', viewValue: 'Bhaktapur'},
    {value: 'pyuthan-2', viewValue: 'Pyuthan'}
  ]

  /**
   * Function - get coordonates for the specific city and
   * see the city on map
   */
  public cityValue: string;

  /**
   * Trigger function from select form
   * @param value
   */
  public trigger(value) {
    this.cityValue = value;
    console.log(value);
  }

  /**
   * Calls initMap with location coordinates
   */
  goToCity() {
    if (this.cityValue === 'kathmandu-0') {
      this.initMap(27.700769, 85.300140);
    } else if (this.cityValue === 'bhaktapur-1') {
      this.initMap(27.6673400, 85.4167300);
    } else if (this.cityValue === 'pyuthan-2') {
      this.initMap(28.083333, 82.83333);
    }
  }

  /**
   * Set longitude and latitude for a city/district
   * @param input1 - latitude
   * @param input2 - longitude
   */
  initMap(input1: number, input2: number) {
    this.lat = input1;
    this.lng = input2;

  }

  constructor() {
  }

  ngOnInit() {
  }

}
