import { Component, OnInit } from '@angular/core';
import { LocationService } from '../../services/location.service';
import { Location } from '../../model/location.model';
import { EarthquakeService } from '../../services/earthquake.service';
import { Earthquake } from '../../model/earthquake.model';
import { OngService } from '../../services/ong.service';
import { Ong } from '../../model/ong.model';
import { isNumeric } from 'rxjs/util/isNumeric';


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
  public magnitudeSelectedValue: number;
  public earthquakeList = Array<Earthquake>();
  public earthquakeMagnitudeList = Array<Earthquake>();
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
  public allOng = new Set<String>();
  public counter: number;
  public mapPopupCheck: number;
  /* MAGNITUDE SEARCH */
  public eqMagnitudeDate: string;
  public eqMagnitudeDepth: number;
  public eqMagnitude: number;

  /**
   * Inject all the services I need
   * @param locationService
   * @param earthquakeService
   * @param ongService
   */
  constructor(private locationService: LocationService, private earthquakeService: EarthquakeService,
              private ongService: OngService) {
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
    /***Move the pin from the map to the desired place **/
    this.initMap(earthquake.latitude, earthquake.longitude);
    /** Call the method to save the list of all the ong from that location id **/
    this.getOngList(earthquake.localizationId.toString());

    /** Go to the coordinates received **/

    /** Sort descending by Date to see the latests Eq **/
    this.earthquakeList.sort(function (obj1: Earthquake, obj2: Earthquake) {
      if (obj1.happenedOn < obj2.happenedOn) {
        return 1;
      }
      if (obj1.happenedOn > obj2.happenedOn) {
        return -1;
      }
      return 0;
    });

    earthquake = new Earthquake(this.earthquakeList[0]);
    this.lastEq = earthquake.happenedOn;


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

    this.numberOfEq = this.earthquakeList.length;
    earthquake = new Earthquake(this.earthquakeList[0]);
    this.highestMagnitude = earthquake.magnitude;

    earthquake = new Earthquake(this.earthquakeList[this.earthquakeList.length - 1]);
    this.lowestMagnitude = earthquake.magnitude;

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
   * Calls the getDistrictData that gets from the db the Location Obj with that District name
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


  /** MAGNITUDE **/

  getMagnitudeList(magnitude: number) {
    this.earthquakeService.getListEarthquakesByMagnitude(magnitude)
      .subscribe(
        (data) => this.retrieveListEqMagnitude(data),
        (err) => this.showError()
      );
  }

  /**
   * Get the earthquake list that have the magnitude that the user
   * selected
   * For the first earthquake the pin from the map is moved to that coordonate
   * @param responseData
   */
  private retrieveListEqMagnitude(responseData: any) {
    this.earthquakeMagnitudeList = [];
    this.counter = 0;
    for (let index in responseData) {
      let earthquake = new Earthquake(responseData[index]);
      this.earthquakeMagnitudeList.push(earthquake);
    }

    /** Move the pin for the first element in the array **/
    let earthquake = new Earthquake(this.earthquakeMagnitudeList[0]);
    this.eqMagnitude = earthquake.magnitude;
    this.eqMagnitudeDate = earthquake.happenedOn.toDateString();
    this.eqMagnitudeDepth = earthquake.depth;

    this.populatePopup(earthquake.localizationId);
    this.initMap(earthquake.latitude, earthquake.longitude);
  }

  /**
   * Function that loops through the eq list while pressing the
   * button Next in the view
   * The pin in the map is moved with the coordinates that the
   * earthquake from that position has
   * We call the function to populate the popup from the pin on the map
   */
  private goToNextPlace() {
    this.counter = this.counter + 1;
    if (this.counter === this.earthquakeMagnitudeList.length) {
      this.counter = 0;
      console.log(this.counter);
      let earthquake = new Earthquake(this.earthquakeMagnitudeList[this.counter]);
      this.eqMagnitude = earthquake.magnitude;
      this.eqMagnitudeDate = earthquake.happenedOn.toDateString();
      this.eqMagnitudeDepth = earthquake.depth;
      this.populatePopup(earthquake.localizationId);
      this.initMap(earthquake.latitude, earthquake.longitude);


    } else {
      console.log(this.counter);
      let earthquake = new Earthquake(this.earthquakeMagnitudeList[this.counter]);
      this.eqMagnitude = earthquake.magnitude;
      this.eqMagnitudeDate = earthquake.happenedOn.toDateString();
      this.eqMagnitudeDepth = earthquake.depth;
      this.populatePopup(earthquake.localizationId);
      this.initMap(earthquake.latitude, earthquake.longitude);
    }
  }

  /**
   * Funtion to be called on click and calls all the function
   * to get the list of eq by magnitude
   */
  goToCityMagnitude() {
    this.getMagnitudeList(this.magnitudeSelectedValue);
  }

  private populatePopup(id: number) {
    this.locationService.getLocationByLocationId(id.toString())
      .subscribe(
        (data) => this.getDataForMagnitudePopup(data),
        (err) => this.showError()
      );
  }

  public districtMagnitude: string;
  public municipalityMagnitude: string;

  private getDataForMagnitudePopup(responseData: any) {
    let location = new Location(responseData);
    this.districtMagnitude = location.district;
    this.municipalityMagnitude = location.municipality;
  }

  /** END MAGNITUDE **/

  /**
   * Save District that the user selected
   * @param value
   */
  public triggerDistrict(value) {
    this.mapPopupCheck = 1;
    this.districtSelectedValue = value;
    console.log(this.districtSelectedValue);
  }

  /**
   * Save Municipality that the user selected
   * @param value
   */
  public triggerMunicipality(value) {
    this.mapPopupCheck = 1;
    this.municipalitySelectedValue = value;
    console.log(this.municipalitySelectedValue);
  }

  /**
   * Save the magnitude selected by the user
   * @param value
   */
  public triggerMagnitude(value) {
    this.mapPopupCheck = 2;
    this.magnitudeSelectedValue = value;
    console.log(this.magnitudeSelectedValue);
  }
  /**
   * Set longitude and latitude for a city/district
   * Move the pin on the map
   * @param input1 - latitude
   * @param input2 - longitude
   */
  initMap(input1: number, input2: number) {
    this.lat = input1;
    this.lng = input2;
  }


  /**** POPUP FROM THE MAP ****/
  /**
   * Return ONG list for that location
   * This is show in the popup from the pin on the map
   * @param localizationId
   */
  getOngList(localizationId: string) {
    this.ongService.getAllOngByLocationId(localizationId)
      .subscribe(
        (data) => this.retrieveOngList(data),
        (err) => this.showError()
      );
  }

  retrieveOngList(responseData: any) {
    this.allOng = new Set<string>();
    for (let index in responseData) {
      let ong = new Ong(responseData[index]);
      if (!isNumeric(ong.ongName)) {
        console.log(ong.ongName);
        this.allOng.add(ong.ongName);
      }
    }
  }
}
