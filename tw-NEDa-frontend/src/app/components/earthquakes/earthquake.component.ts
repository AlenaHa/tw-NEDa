import { AfterViewInit, Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { EarthquakeService } from '../../services/earthquake.service';
import { Earthquake } from '../../model/earthquake.model';
import { MdDialog, MdMenuTrigger } from '@angular/material';
import { EarthquakeDialog } from './earthquake.dialog';

@Component({
  selector: 'filter-demo',
  templateUrl: './earthquake.component.html',
  styleUrls: ['./earthquake.component.scss']
})
export class EarthquakeComponent implements OnInit, AfterViewInit {

  @ViewChild(DatatableComponent) table: DatatableComponent;
  @ViewChild(MdMenuTrigger) trigger: MdMenuTrigger;
  @ViewChild('editTmpl') editTmpl: TemplateRef<any>;
  @ViewChild('hdrTpl') hdrTpl: TemplateRef<any>;


  temp = [];
  rows = [];
  columns = [];

  constructor(private earthquakeService: EarthquakeService,
              public dialog: MdDialog) {
  }


  ngAfterViewInit(): void {
    // Init table's columns
    this.columns = [
      {prop: 'earthquakeId', name: 'Earthquake ID'},
      {prop: 'localizationId', name: 'Location ID'},
      {prop: 'latitude', name: 'Latitude'},
      {prop: 'longitude', name: 'Longitude'},
      {prop: 'depth', name: 'Depth'},
      {prop: 'magnitude', name: 'Magnitude'},
      {prop: 'happenedOn', name: 'Date'},
      {
        cellTemplate: this.editTmpl,
        headerTemplate: this.hdrTpl,
        name: 'Actions',
        width: 30
      }
    ];
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

  /**
   *
   */
  addEarthquake() {
    let dialogRef = this.dialog.open(EarthquakeDialog);

    dialogRef.afterClosed().subscribe(newEarthquake => {
      if (newEarthquake) {
        console.log("New earthquake received!");
        console.log(newEarthquake);

        this.earthquakeService
          .addEarthquake(newEarthquake)
          .subscribe(
            (data) => {
              console.log(data);
              let createdEarthquake = new Earthquake(data);
              this.rows.push(createdEarthquake);
              this.temp.push(createdEarthquake);
            },
            (err) => {
              console.log("error");
            });
      }
    });
  }

  updateFilter(event) {
    const val = event.target.value;

    // filter our data
    const temp = this.temp.filter(function (d) {
      return d.earthquakeId.toString().toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.rows = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

  /**
   * Handles table's context menu event
   * @param contextMenuEvent
   */
  onContextMenu(contextMenuEvent: any) {
    console.log(contextMenuEvent);

    // Opens the menu
    this.trigger.openMenu();

    contextMenuEvent.event.preventDefault();
    contextMenuEvent.event.stopPropagation();
  }

  /**
   * Handle edit
   * @param earthquake
   */
  edit(earthquake: any) {
    console.log(earthquake);

    let earthquakeIndex = earthquake.$$index;
    console.log("Editing row: " +  earthquakeIndex);

    let dialogRef = this.dialog.open(EarthquakeDialog);
    dialogRef.componentInstance.earthquake = new Earthquake(earthquake);

    dialogRef.afterClosed().subscribe(newEarthquake => {
      if (newEarthquake) {
        console.log("Edited earthquake received!");
        console.log(newEarthquake);

        // Data must be merge so reference to the row that is received as a param is kept (this way the table is auto updated)
        earthquake.localizationId = newEarthquake.localizationId;
        earthquake.latitude = newEarthquake.latitude;
        earthquake.longitude = newEarthquake.longitude;
        earthquake.depth = newEarthquake.depth;
        earthquake.magnitude = newEarthquake.magnitude;
        earthquake.happenedOn = newEarthquake.happenedOn;

      } else {
        console.log("Dialog was cancelled.")
      }
    });

  }

  /**
   * Handle delete
   * @param earthquake
   */
  delete(earthquake: any) {
    console.log(earthquake);
    console.log("Deleting row: " + earthquake.$$index);

    // TODO: implement confirmation dialog
  }
}
