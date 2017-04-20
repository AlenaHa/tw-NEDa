import {Component} from "@angular/core";
import {MdDialogRef} from "@angular/material";
import {Earthquake} from "../../model/earthquake.model";

@Component({
  selector: 'earthquake-dialog',
  templateUrl: './earthquake.dialog.html',
  styleUrls: ['./earthquake.dialog.css']
})
export class EarthquakeDialog {

  public earthquake: Earthquake = new Earthquake({});

  constructor(public dialogRef: MdDialogRef<EarthquakeDialog>) {
  }

  close(closeType: boolean) {
    console.log(closeType);
    this.dialogRef.close(closeType);
    console.log(this.earthquake);
  }
}
