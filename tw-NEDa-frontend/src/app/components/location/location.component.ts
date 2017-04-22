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
  public districtValue: string;
  public municipalityValue: string;
  public nrOfEq: number;
  public lastEq: number;
  public highestMagnitude;
  public lowestMagnitude;

  /**
   * Trigger function from select form
   * @param value
   */
  public triggerDistrict(value) {
    this.districtValue = value;
    console.log(value);
  }

  public triggerMunicipality(value) {
    this.municipalityValue = value;
    console.log(value);
  }

  /**
   * using keyup in html I called this function
   * keep in val what the user wrote
   *
   * @param event
   */
  //TODO: validate input(check if is numeric)and make magnitude case(?)
  checkMagnitude(event) {
    const val = event.target.value;
    console.log(val);
  }

  /**
   * Calls initMap with location coordinates
   * Search By Location
   */
  goToDistrict() {
    if (this.districtValue === 'kathmandu-0') {
      this.initMap(27.700769, 85.300140);
    } else if (this.districtValue === 'bhaktapur-1') {
      this.initMap(27.6673400, 85.4167300);
    } else if (this.districtValue === 'pyuthan-2') {
      this.initMap(28.083333, 82.83333);
    }
  }

  goToMunicipality() {
    if (this.municipalityValue === 'kathmandu-0') {
      this.initMap(27.700769, 85.300140);
    } else if (this.municipalityValue === 'bhaktapur-1') {
      this.initMap(27.6673400, 85.4167300);
    } else if (this.municipalityValue === 'pyuthan-2') {
      this.initMap(28.083333, 82.83333);
    }
  }

  /**
   * Search By Magnitude
   */
  goToCityMagnitude() {

  }

  /**
   * Set longitude and latitude for a city/district
   * @param input1 - latitude
   * @param input2 - longitude
   */
  initMap(input1: number, input2: number) {
    this.lat = input1;
    this.lng = input2;

    /**
     * populate pseudo-information for the info card
     */
    this.nrOfEq = 100;
    this.lastEq = Date.now();
    this.highestMagnitude = 8.9;
    this.lowestMagnitude = 2.3;

  }


  constructor() {
  }

  ngOnInit() {
  }

}
