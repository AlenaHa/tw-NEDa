import {Component, ViewChild, OnInit} from '@angular/core';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { EarthquakeService } from '../../services/earthquake.service';
import { Earthquake } from '../../model/earthquake.model';

@Component({
  selector: 'filter-demo',
  templateUrl: './earthquake.component.html',
  styleUrls: ['./earthquake.component.scss']
})
export class EarthquakeComponent implements OnInit {

  rows = [];

  temp = [];

  columns = [
    { prop: 'earthquakeId', name: 'Earthquake ID' },
    { prop: 'localizationId', name: 'Location ID' },
    { prop: 'latitude', name: 'Latitude' },
    { prop: 'longitude', name: 'Longitude' },
    { prop: 'depth', name: 'Depth' },
    { prop: 'magnitude', name: 'Magnitude' },
    { prop: 'happenedOn', name: 'Date' },
  ];

  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor(private earthquakeService: EarthquakeService) {

  }


  ngOnInit(): void {
    this.earthquakeService.getAllEarthquakes()
      .subscribe(
        (data) => this.retrieveData(data),
        (err) => this.showError());
  }

  retrieveData(responseData: any) {
    let allEarthquakes = [];

    for (let index in responseData) {
      let earthquake = new Earthquake(responseData[index]);
      allEarthquakes.push(earthquake);
    }

    this.temp = [...allEarthquakes];

    // Populate the table
    this.rows = allEarthquakes;
  }

  showError() {
    console.log("Failed to retrieve data from server.")
  }

  updateFilter(event) {
    const val = event.target.value;

    // filter our data
    const temp = this.temp.filter(function(d) {
      return d.earthquakeId.toString().toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.rows = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

}
