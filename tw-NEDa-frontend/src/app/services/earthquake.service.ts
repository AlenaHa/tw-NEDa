/**
 * @author Elena Hardon
 * @date 11/04/2017
 */
import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
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
      this.backendUrl + '/earthquake/all',
      { headers: headers})
      .map(res => res.json());

  }
}
