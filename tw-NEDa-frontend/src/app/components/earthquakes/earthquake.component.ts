import { AfterViewInit, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { EarthquakeService } from '../../services/earthquake.service';
import { Earthquake } from '../../model/earthquake.model';
import { MdDialog, MdMenuTrigger } from '@angular/material';
import { EarthquakeDialog } from './earthquake.dialog';
import { CompleteEarthquake } from '../../model/completeEarthquake.model';

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
      {prop: 'latitude', name: 'Latitude'},
      {prop: 'longitude', name: 'Longitude'},
      {prop: 'depth', name: 'Depth'},
      {prop: 'magnitude', name: 'Magnitude'},
      {prop: 'happenedOn', name: 'Date'},
      {prop: 'district', name: 'Earthquake District'},
      {prop: 'municipality', name: 'Earthquake Municipality'},
      {
        cellTemplate: this.editTmpl,
        headerTemplate: this.hdrTpl,
        name: 'Actions',
        width: 30
      }
    ];
  }

  ngOnInit(): void {
    // Get all the earthquakes from the backend
    this.earthquakeService.getAllCompleteEarthquales()
      .subscribe(
        (data) => this.retrieveData(data),
        (err) => this.showError());

  }

  /**
   * Adds the data retrieved from the server to the table
   * @param responseData
   */
  retrieveData(responseData: any) {
    let allEarthquakes = [];

    for (let index in responseData) {
      let earthquake = new CompleteEarthquake(responseData[index]);
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
   * Adds a new earthquake.
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

  /**
   * Updates table's filter
   * @param event
   */
  updateFilter(event) {
    const val = event.target.value;

    // Filter the data
    const temp = this.temp.filter(function (d) {
      return d.earthquakeId.toString().toLowerCase().indexOf(val) !== -1 || !val;
    });

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
    console.log("Editing row: " + earthquakeIndex);

    // TODO: dialog should be opened with different title when editing
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
    // TODO: implement confirmation dialog

    console.log(earthquake);
    console.log("Deleting row: " + earthquake.$$index);

    for (let index = 0; index < this.table.rows.length; index++) {
      let eq = this.table.rows[index];
      // console.log(eq);
      if (eq.earthquakeId === earthquake.earthquakeId) {

        console.log('found it');
        // TODO call the server and delete in UI **ONLY IF** that succeded.
        // You don't want to delete on UI if the delete on the DB failed, right?
        this.table.rows.splice(index, 1);

        return;
      }

    }

  }

  /**just for presentation**/
  public magnitude: number = 8.9;
  public place: string = 'Kathmandu';
  public depth: string = '12 km';


  public year: any;

  checkYear(event) {
    this.year = event.target.value;
    console.log(this.year);
  }

}
