/**
 * Created by Cami on 2017-04-20.
 *
 */
import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
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
}