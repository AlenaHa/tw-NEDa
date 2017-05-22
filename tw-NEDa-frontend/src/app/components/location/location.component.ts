import { Component, OnInit } from '@angular/core';
import { LocationService } from '../../services/location.service';
import { Location } from '../../model/location.model';

// TODO: (Cati) Move this in a model .ts file under the model folder
export interface coordinates {
  place: string;
  lat: number;
  lng: number;
}

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})


export class LocationComponent implements OnInit {

  /** latitude and longitude for the map (Nepal coordonates) **/
  public lat: number = 28;
  public lng: number = 84;
  /** to save all the Districts & Municilaties from db **/
  public allDistricts = Array<string>();
  public allMunicipalities = Array<string>();
  public districtSelectedValue: string;
  public municipalitySelectedValue: string;

  constructor(private locationService: LocationService) {
  }

  /**
   * On init save into allDistrict & allMunicipalities the content from db
   */
  ngOnInit() {
    this.locationService.getAllLocations()
      .subscribe(
        (data) => this.retrieveDataLocation(data),
        (err) => this.showError());
  }


  /********************** DISTRICT ****************/
  /**
   * Calls the getDistrictData that gets from the db the Locaiton Obj with that District name
   * @param district
   */
  getDistrict(district: string) {
    this.locationService.getDistrictData(district)
      .subscribe(
        (data) => this.retrieveDistrict(data),
        (err) => this.showError()
      );
  }


  retrieveDistrict(responseData: any) {
    let location = new Location(responseData);
    console.log(location.municipality);
    console.log(location.district);
  }

  goToDistrict() {
    this.getDistrict(this.districtSelectedValue);
  }

  /*********************MUNICIPALITY *************/
  getMunicipality(municipality: string) {
    this.locationService.getMunicipalityData(municipality)
      .subscribe(
        (data) => this.retrieveMunicipality(data),
        (err) => this.showError()
      );
  }

  retrieveMunicipality(responseData: any) {
    let location = new Location(responseData);
    console.log(location.municipality);
    console.log(location.district);
  }

  goToMunicipality() {
    this.getMunicipality(this.municipalitySelectedValue);
  }

  /**
   * Save District & Municipalities into arrays to show in view
   * @param responseData
   */
  retrieveDataLocation(responseData: any) {

    for (let index in responseData) {
      let location = new Location(responseData[index]);
      this.allDistricts.push(location.district);
      this.allMunicipalities.push(location.municipality);
    }

  }

  showError() {
    console.log("Error fetching data from server!");
  }


  /**
   * Save District that the user selected
   * @param value
   */
  public triggerDistrict(value) {
    this.districtSelectedValue = value;
    console.log(this.districtSelectedValue);
  }

  /**
   * Save Municipality that the user selected
   * @param value
   */
  public triggerMunicipality(value) {
    this.municipalitySelectedValue = value;
    console.log(this.municipalitySelectedValue);
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

}
