/**
 * Created by Cami on 2017-04-21.
 */
import { Component } from '@angular/core';
import { PopulationService } from '../../services/population.service';
import 'chart.js/dist/Chart.bundle.min.js';
import { Population } from '../../model/population.model';
import { Location } from '../../model/location.model';
import { LocationService } from '../../services/location.service';
import any = jasmine.any;
import { isNumber } from 'util';

@Component({
  selector: 'filter-demo',
  templateUrl: './population.component.html',
  styleUrls: ['./population.component.scss'],
})

export class PopulationComponent {

  public selectedValue: string;
  public allDistrictNames = new Set<string>();
  public numberFemale: any
  public numberMale: any
  public age_15: any
  public age_25: any
  public age_40: any
  public age_55: any


  constructor(private populationService: PopulationService, private locationService: LocationService) {
  }

  ngOnInit(): void {
    this.locationService.getAllLocations()
      .subscribe(
        (data) => this.retrieveLocationData(data),
        (err) => this.showError());

  }

  retrieveLocationData(responseData: any) {
    let allDistricts = [];

    for (let index in responseData) {
      let pop = new Location(responseData[index]);
      this.allDistrictNames.add(pop.district);
    }
  }

  showError() {
    console.log("Error fetching data from server!");
  }

  public triggerDistrict(value) {
    this.selectedValue = value;
    console.log(this.selectedValue);
    console.log();
  }


  goToCharts() {
    this.getFemaleNumber(this.selectedValue);
    this.getMaleNumber(this.selectedValue);

    this.age15(this.selectedValue);
    this.age25(this.selectedValue);
    this.age40(this.selectedValue);
    this.age55(this.selectedValue);
  }

  /*************Doughnut*****************/
  getFemaleNumber(district: string) {
    this.populationService.getFemaleNumber(district)
      .subscribe(
        (data) => this.retrieveFemale(data),
        (err) => this.showError()
      );
  }

  retrieveFemale(responseData: any) {
    this.numberFemale = responseData;
    console.log(this.numberFemale);
    // this.doughnutChartData = [this.numberFemale, this.numberMale]
  }

  getMaleNumber(district: string) {
    this.populationService.getMaleNumber(district)
      .subscribe(
        (data) => this.retrieveMale(data),
        (err) => this.showError()
      );
  }

  retrieveMale(responseData: any) {
    this.numberMale = responseData;
    console.log(this.numberMale);
    this.doughnutChartData = [this.numberFemale + 1, this.numberMale + 1]
  }

  /*************PolarArea*****************/
  age15(district: string) {
    this.populationService.getAge15(district)
      .subscribe(
        (data) => this.retrieveAge15(data),
        (err) => this.showError()
      );
  }

  retrieveAge15(responseData: any) {
    this.age_15 = responseData;
    console.log(this.age_15);
    this.polarAreaChartData = [this.age_15, this.age_25, this.age_40, this.age_55];

  }


  age25(district: string) {
    this.populationService.getAge15(district)
      .subscribe(
        (data) => this.retrieveAge25(data),
        (err) => this.showError()
      );
  }

  retrieveAge25(responseData: any) {
    this.age_25 = responseData;
    console.log(this.age_25);
    this.polarAreaChartData = [this.age_15, this.age_25, this.age_40, this.age_55];

  }


  age40(district: string) {
    this.populationService.getAge40(district)
      .subscribe(
        (data) => this.retrieveAge40(data),
        (err) => this.showError()
      );
  }

  retrieveAge40(responseData: any) {
    this.age_40 = responseData;
    console.log(this.age_40);
    this.polarAreaChartData = [this.age_15, this.age_25, this.age_40, this.age_55];

  }


  age55(district: string) {
    this.populationService.getAge55(district)
      .subscribe(
        (data) => this.retrieveAge55(data),
        (err) => this.showError()
      );
  }

  retrieveAge55(responseData: any) {
    this.age_55 = responseData;
    console.log(this.age_55);

    this.polarAreaChartData = [this.age_15, this.age_25, this.age_40, this.age_55];
  }


  // PolarArea
  public polarAreaChartLabels: string[] = ['15-24', '25-39', '40-54', '55-60'];
  public polarAreaChartData: number[] = [this.age_15, this.age_25, this.age_40, this.age_55];
  // public polarAreaLegend:boolean = true;
  public polarAreaChartType: string = 'polarArea';


  // Doughnut
  public doughnutChartLabels: string[] = ['Female', 'Male'];
  public doughnutChartData: number[] = [this.numberFemale, this.numberMale];
  public doughnutChartType: string = 'doughnut';


  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

}

