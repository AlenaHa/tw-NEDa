import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

@Injectable()
export class LocationService {

  private backendUrl: String;

  constructor(private http: Http) {
    this.backendUrl = "http://localhost:8666/neda"
  }

  /** get the list with all the data from location table**/
  getAllLocations() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/location/all',
      {headers: headers})
      .map(res => res.json());
  }

  /**
   * Get data Location Object only for this particular district
   * @param district
   * @returns {Observable<R>}
   */
  getDistrictData(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/location/district/' + district,
      {headers: headers})
      .map(res => res.json());

  }

  /**
   * Get data Location Object for this particular municipality
   * @param municipality
   * @returns {Observable<R>}
   */
  getMunicipalityData(municipality: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/location/municipality/' + municipality,

      {headers: headers})
      .map(res => res.json());

  }
}
