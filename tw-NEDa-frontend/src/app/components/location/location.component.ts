import { Component, OnInit } from '@angular/core';


export interface coordinates {
  place: string;
  lat: number;
  lng: number;
}
;

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
   * Createad array of object in which we keep the coordinates for a district/municipality
   * @type {[{place: string; lat: number; lng: number},{place: string; lat: number; lng: number},{place: string; lat: number; lng: number}]}
   */
  public myCoordinates: Array<coordinates> = [
    {place: 'kathmandu-0', lat: 27.700769, lng: 85.300140},
    {place: 'bhaktapur-1', lat: 27.6673400, lng: 85.4167300},
    {place: 'pyuthan-2', lat: 28.083333, lng: 82.83333}
  ];
  public selectedCoordinates: coordinates;


  /**
   * Trigger function from select form
   * @param value
   */
  public triggerDistrict(value) {
    this.districtValue = value;
    /**
     * Find the coordinates for the selected value
     * @type {any|coordinates}
     */
    this.selectedCoordinates = this.myCoordinates.find(item => item.place === this.districtValue);
    console.log(value);
    console.log(this.selectedCoordinates.lat, this.selectedCoordinates.lng);

  }

  public triggerMunicipality(value) {
    this.municipalityValue = value;
    this.selectedCoordinates = this.myCoordinates.find(item => item.place === this.municipalityValue);
    console.log(value);
    console.log(this.selectedCoordinates.lat, this.selectedCoordinates.lng);
  }

  /**
   * using keyup in html I called this function
   * keep in val what the user wrote
   * We will show on map all the earthquakes that have the magnitude inserted by the user
   * @param event
   */
  //TODO: validate input(check if is numeric)and make magnitude case(?)
  //TODO: see how to make more markers for google map(show more pins on the map)
  checkMagnitude(event) {
    const val = event.target.value;
    console.log(val);
  }

  /**
   * Calls initMap with location coordinates
   * Search By Location
   */
  goToDistrict() {

    this.initMap(this.selectedCoordinates.lat, this.selectedCoordinates.lng);
    /**
     * populate pseudo-information for the info card
     */
    this.nrOfEq = 80;
    this.lastEq = Date.now();
    this.highestMagnitude = 8;
    this.lowestMagnitude = 2;
  }

  goToMunicipality() {

    this.initMap(this.selectedCoordinates.lat, this.selectedCoordinates.lng);
    /**
     * populate pseudo-information for the info card
     */
    this.nrOfEq = 100;
    this.lastEq = Date.now();
    this.highestMagnitude = 8.9;
    this.lowestMagnitude = 2.3;
  }

  /**
   * Search By Magnitude
   */

  goToCityMagnitude() {
    console.log("ceva");
    this.initMap(27.700769, 85.300140);

    console.log("ceva");
    this.initMap(27.6673400, 85.4167300);

    console.log("ceva");
    this.initMap(28.083333, 82.83333);

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
