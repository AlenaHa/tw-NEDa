/**
 * @author Elena Hardon
 * @date 11/04/2017
 */
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Earthquake } from '../model/earthquake.model';
@Injectable()
export class EarthquakeService {

  private backendUrl: String;

  constructor(private http: Http) {
    this.backendUrl = "http://localhost:8666/neda"
  }

  getAllEarthquakes() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/earthquakes/all',
      {headers: headers})
      .map(res => res.json());
  }

  getAllCompleteEarthquales() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/earthquakes/complete',
      {headers: headers})
      .map(res => res.json());
  }

  /**
   * Get the list of Earthquakes by LocationId
   * @param locationId
   * @returns {Observable<R>}
   */
  getListEarthquake(locationId: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/earthquakes/location/' + locationId,
      {headers: headers})
      .map(res => res.json());

  }

  /**
   * Get the latest Earthquake
   * @returns {Observable<R>}
   */
  getLatestEarthquake() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/earthquakes/latest',
      {headers: headers})
      .map(res => res.json());

  }

  /**
   * Get the list of earthquakes that have the magnitude
   * from the parameter
   * @param magnitude
   * @returns {Observable<R>}
   */
  getListOfEarthquakesByMagnitude(magnitude: number) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.get(
      this.backendUrl + '/earthquakes/complete/magnitude/' + magnitude,
      {headers: headers})
      .map(res => res.json()
      );
  }

  /**
   * Get the list of earthquake thate have the magnitude -> used in location
   * from the parameter
   * @param magnitude
   * @returns {Observable<R>}
   */
  getListEarthquakesByMagnitude(magnitude: number) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.get(
      this.backendUrl + '/earthquakes/magnitude/' + magnitude,
      {headers: headers})
      .map(res => res.json()
      );
  }

  getListOfEarthquakesByDepth(depth: number) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.get(
      this.backendUrl + '/earthquakes/depth/' + depth,
      {headers: headers})
      .map(res => res.json()
      );
  }

  getListOfEarthquakesByDistrict(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.get(
      this.backendUrl + '/earthquakes/district/' + district,
      {headers: headers})
      .map(res => res.json()
      );
  }
  /**
   * I think this should be deleted (?) - we don't add eq
   * @param earthquake
   * @returns {Observable<R>}
   */
  addEarthquake(earthquake: Earthquake) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let earthquakeString = JSON.stringify(earthquake);

    return this.http.post(
      this.backendUrl + '/earthquake',
      earthquakeString,
      {headers: headers})
      .map(res => res.json());
  }

  getCsv() {
    return this.backendUrl + '/earthquakes/db.csv';
  }

   getAllDistricts(){
     let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.get(
          this.backendUrl + '/districts/',
          {headers: headers})
          .map(res => res.json());
  }
}
