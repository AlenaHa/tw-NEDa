/**
 * Created by Cami on 2017-04-20.
 *
 */
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Ong } from '../model/ong.model';
@Injectable()
export class OngService {

  private backendUrl: String;

  constructor(private http: Http) {
    this.backendUrl = "http://localhost:8666/neda"
  }

  getAllOng() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/ong/all',
      {headers: headers})
      .map(res => res.json());
  }

  /**
   * Get the list with all the Ong that are in that Location Id
   * @param locationId
   * @returns {Observable<R>}
   */
  getAllOngByLocationId(locationId: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/ong/location/' + locationId,
      {headers: headers})
      .map(res => res.json());

  }

  getOngNameData(ongName: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/ong/ongName/' + ongName,
      {headers: headers})
      .map(res => res.json());

  }

  addOng(ong: Ong) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let ongString = JSON.stringify(ong);

    return this.http.post(
      this.backendUrl + '/ong',
      ongString,
      {headers: headers})
      .map(res => res.json());
  }

  getAllOngDetails() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/ong/ongDetails',
      {headers: headers})
      .map(res => res.json());

  }
}
