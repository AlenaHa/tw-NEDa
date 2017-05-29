import { Component, OnInit } from '@angular/core';
import { EarthquakeService } from '../../services/earthquake.service';
import { Earthquake } from '../../model/earthquake.model';
import { LocationService } from '../../services/location.service';
import { Location } from '../../model/location.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public hour: number;
  public magnitude: number;
  public day: number;
  public month: number;
  public district: string;
  public time: number;
  constructor(private earthquakeService: EarthquakeService, private locationService: LocationService) {
  }

  /**
   * When page is loaded make a request to get the latest earthquake
   */
  ngOnInit() {

    this.earthquakeService.getLatestEarthquake()
      .subscribe(
        (data) => this.retrieveEarthquake(data),
        (err) => this.showError()
      );
  }

  /**
   * Function that retrieve the data that we got from the request
   * @param retrieveData
   */
  private retrieveEarthquake(retrieveData: any) {
    let earthquake = new Earthquake(retrieveData);

    this.getLocation(earthquake.localizationId.toString());
    // this.hour = earthquake.happenedOn.getHours();
    this.day = earthquake.happenedOn.getDate();
    this.month = earthquake.happenedOn.getMonth();
    this.magnitude = earthquake.magnitude;
    console.log(earthquake.happenedOn);
  }


  /**
   * Get the location for the latest eq
   * @param id
   */
  private getLocation(id: string) {
    this.locationService.getLocationByLocationId(id)
      .subscribe(
        (data) => this.retrieveLocation(data),
        (err) => this.showError()
      );
  }

  private retrieveLocation(responseData: any) {
    let location = new Location(responseData);
    this.district = location.district;
  }

  showError(): void {
    console.log("Couldn't fetch data from server");
  }
}
