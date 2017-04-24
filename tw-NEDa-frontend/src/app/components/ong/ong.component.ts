import { AfterViewInit, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { OngService } from '../../services/ong.service';
import { Ong } from '../../model/ong.model';
import { MdDialog, MdMenuTrigger } from '@angular/material';
import { OngDialog } from './ong.dialog';


@Component({
  selector: 'filter-demo',
  templateUrl: './ong.component.html',
  styleUrls: ['./ong.component.scss'],

})

export class OngComponent implements OnInit, AfterViewInit {
  OngService: any;

  @ViewChild(DatatableComponent) table: DatatableComponent;
  @ViewChild(MdMenuTrigger) trigger: MdMenuTrigger;
  @ViewChild('editTmpl') editTmpl: TemplateRef<any>;
  @ViewChild('hdrTpl') hdrTpl: TemplateRef<any>;


  temp = [];
  rows = [];
  columns = [];

  constructor(private ongService: OngService, public dialog: MdDialog) {
  }


  ngAfterViewInit(): void {
    // Init table's columns
    this.columns = [
      {prop: 'location', name: 'Location'},
      {prop: 'ongName', name: 'Ong Name'},
      {prop: 'activityType', name: 'Activity Type'},
      {prop: 'activitySubtype', name: 'Activity Subtype'},
      {prop: 'supplies', name: 'Supplies'},
      {
        cellTemplate: this.editTmpl,
        headerTemplate: this.hdrTpl,
        name: 'Actions',
        width: 100
      }
    ];
  }

  ngOnInit(): void {
    this.ongService.getAllOng()
      .subscribe(
        (data) => this.retrieveData(data),
        (err) => this.showError());
  }

  retrieveData(responseData: any) {
    let allOng = [];

    for (let index in responseData) {
      let ong = new Ong(responseData[index]);
      allOng.push(ong);
    }

    this.temp = [...allOng];

    // Populate the table
    this.rows = allOng;
  }

  showError() {
    console.log("Failed to retrieve data from server.")
  }

  /**
   *
   */
  donateNow() {
    let dialogRef = this.dialog.open(OngDialog);

    dialogRef.afterClosed().subscribe(newong => {
      if (newong) {
        console.log("Success!");
      }
    });
  }

  updateFilter(event) {
    const val = event.target.value;

    // filter our data
    const temp = this.temp.filter(function (d) {
      return d.ongId.toString().toLowerCase().indexOf(val) !== -1 || !val;
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

  edit(ong: any) {
    console.log(ong);

    let ongIndex = ong.$$index;
    console.log("Editing row: " + ongIndex);

  }

  delete(ong: any) {
    // TODO: implement confirmation dialog

    console.log(ong);
    console.log("Deleting row: " + ong.$$index);

    for (let index = 0; index < this.table.rows.length; index++) {
      let eq = this.table.rows[index];
      // console.log(eq);
      if (eq.ongId === ong.ongId) {

        console.log('found it');
        // TODO call the server and delete in UI **ONLY IF** that succeded.
        // You don't want to delete on UI if the delete on the DB failed, right?
        this.table.rows.splice(index, 1);

        return;
      }

    }

  }
}

