import { Component, OnInit } from '@angular/core';
import { LocationService } from '../../services/location.service';
import { Location } from '../../model/location.model';
import { EarthquakeService } from '../../services/earthquake.service';
import { Earthquake } from '../../model/earthquake.model';

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
  public allMagnitudes = new Set<number>();
  public districtSelectedValue: string;
  public municipalitySelectedValue: string;
  public earthquakeList = Array<Earthquake>();
  public highestMagnitude: number;
  public lowestMagnitude: number;
  public numberOfEq: number;
  public lastEq: Date;
  public oneMagnitude: number;
  public twoMagnitude: number;
  public threeMagnitude: number;
  public oneYear: number;
  public twoYear: number;
  public threeYear: number;

  constructor(private locationService: LocationService, private earthquakeService: EarthquakeService) {
  }

  /**
   * On init save into allDistrict & allMunicipalities & all the magnitudes
   * found in the database
   *
   */
  ngOnInit() {
    this.locationService.getAllLocations()
      .subscribe(
        (data) => this.retrieveDataLocation(data),
        (err) => this.showError());
    this.earthquakeService.getAllEarthquakes()
      .subscribe(
        (date) => this.retrieveMagnitude(date),
        (err) => this.showError()
      );
  }

  /**
   * Save District & Municipalities & Magnitudes into arrays to show in view
   * @param responseData
   */
  retrieveDataLocation(responseData: any) {

    for (let index in responseData) {
      let location = new Location(responseData[index]);
      this.allDistricts.push(location.district);
      this.allMunicipalities.push(location.municipality);
    }

  }

  /**
   * Error message function
   */
  showError() {
    console.log("Error fetching data from server!");
  }

  /**
   * Make the magnitudes Set with unique values
   * @param responseData
   */
  retrieveMagnitude(responseData: any) {
    for (let index in responseData) {
      let earthquake = new Earthquake(responseData[index]);
      this.allMagnitudes.add(earthquake.magnitude);
    }

  }


  /**
   * Get the list of earthquake from that idLocation
   * @param locationId
   */
  getEarthquakeListByLocationId(locationId: string) {
    this.earthquakeService.getListEarthquake(locationId)
      .subscribe(
        (data) => this.retrieveListEq(data),
        (err) => this.showError()
      );
  }

  /**
   * Make a new Earthquake object where we save the list
   * @param responseData
   */
  retrieveListEq(responseData: any) {

    this.earthquakeList = [];

    /**Make the array with Eq **/
    for (let index in responseData) {
      let earthquake = new Earthquake(responseData[index]);
      this.earthquakeList.push(earthquake);
    }

    let earthquake = new Earthquake(this.earthquakeList[0]);
    /** Go to the coordinates received **/
    this.initMap(earthquake.latitude, earthquake.longitude);


    /** Sort descending by magnitude to see the highest magnitude **/
    this.earthquakeList.sort(function (obj1: Earthquake, obj2: Earthquake) {
      if (obj1.happenedOn < obj2.happenedOn) {
        return 1;
      }
      if (obj1.happenedOn > obj2.happenedOn) {
        return -1;
      }
      return 0;
    });

    this.numberOfEq = this.earthquakeList.length;
    earthquake = new Earthquake(this.earthquakeList[0]);
    this.highestMagnitude = earthquake.magnitude;
    earthquake = new Earthquake(this.earthquakeList[this.earthquakeList.length - 1]);
    this.lowestMagnitude = earthquake.magnitude;


    /** Sort descending by Date to see the last Eq **/
    this.earthquakeList.sort(function (obj1: Earthquake, obj2: Earthquake) {
      if (obj1.magnitude < obj2.magnitude) {
        return 1;
      }
      if (obj1.magnitude > obj2.magnitude) {
        return -1;
      }
      return 0;
    });

    earthquake = new Earthquake(this.earthquakeList[0]);
    this.lastEq = earthquake.happenedOn;
    /** With the array sorted by magnitude descending call the function
     * to make top 3 eq
     */
    this.topThreeContent(this.earthquakeList);
  }

  /**
   * Top three infoBox
   * @param earthquakeList
   */
  topThreeContent(earthquakeList: Array<Earthquake>) {

    this.oneMagnitude = earthquakeList[0].magnitude;
    this.oneYear = earthquakeList[0].happenedOn.getFullYear();
    console.log(this.oneYear);
    this.twoMagnitude = earthquakeList[1].magnitude;
    this.twoYear = earthquakeList[1].happenedOn.getFullYear();
    console.log(this.twoYear);
    this.threeMagnitude = earthquakeList[2].magnitude;
    this.threeYear = earthquakeList[2].happenedOn.getFullYear();
    console.log(this.threeYear);
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

  /**
   * Get the Location object with the wanted District
   * Calls the method to get all the earthquakes from that area
   * @param responseData
   */
  retrieveDistrict(responseData: any) {
    let location = new Location(responseData);
    this.municipalitySelectedValue = location.district;
    console.log(location.municipality);
    console.log(location.district);
    this.getEarthquakeListByLocationId(location.locationId.toString());
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
    this.districtSelectedValue = location.district;
    console.log(location.municipality);
    console.log(location.district);
    this.getEarthquakeListByLocationId(location.locationId.toString());

  }

  goToMunicipality() {
    this.getMunicipality(this.municipalitySelectedValue);
  }

  /**************************End Municipality******/



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
