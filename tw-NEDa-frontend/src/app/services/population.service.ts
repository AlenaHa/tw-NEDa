/**
 * Created by Cami on 2017-04-21.
 */
/**
 * @author Elena Hardon
 * @date 11/04/2017
 */
import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Population } from '../model/population.model';
@Injectable()
export class PopulationService {

  private backendUrl: String;

  constructor(private http: Http) {
    this.backendUrl = "http://localhost:8666/neda"
  }

  getAllPopulation() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/population/all',
      {headers: headers})
      .map(res => res.json());
  }

  addPopulation(population: Population) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let populationString = JSON.stringify(population);

    return this.http.post(
      this.backendUrl + '/population',
      populationString,
      {headers: headers})
      .map(res => res.json());
  }

  getFemaleNumber(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/population/female/' + district,
      {headers: headers})
      .map(res => res.json());

  }

  getMaleNumber(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/population/male/' + district,
      {headers: headers})
      .map(res => res.json());

  }

  getAge15(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/population/age/15/' + district,
      {headers: headers})
      .map(res => res.json());

  }

  getAge25(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/population/age/25/' + district,
      {headers: headers})
      .map(res => res.json());

  }

  getAge40(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/population/age/40/' + district,
      {headers: headers})
      .map(res => res.json());

  }

  getAge55(district: string) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      this.backendUrl + '/population/age/55/' + district,
      {headers: headers})
      .map(res => res.json());

  }
}
