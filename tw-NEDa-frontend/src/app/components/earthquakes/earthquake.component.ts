import {Component, ViewChild} from '@angular/core';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { eqdata } from "./earthquake.data";

@Component({
  selector: 'filter-demo',
  templateUrl: './earthquake.component.html',
  styleUrls: ['./earthquake.component.scss']
})
export class EarthquakeComponent {

  rows = [];

  temp = [];

  columns = [
    { prop: 'name' },
    { name: 'Company' },
    { name: 'Gender' }
  ];
  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor() {

    // TODO dummy data
    let data = eqdata;
      this.temp = [...data];

      // push our inital complete list
      this.rows = data;

  }

  fetch(cb) {
    const req = new XMLHttpRequest();
    req.open('GET', `assets/data/company.json`);

    req.onload = () => {
      cb(JSON.parse(req.response));
    };

    req.send();
  }

  updateFilter(event) {
    // TODO filter server side
    const val = event.target.value;

    // filter our data
    const temp = this.temp.filter(function(d) {
      return d.name.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.rows = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

}
