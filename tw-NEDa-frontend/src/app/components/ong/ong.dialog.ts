/**
 * Created by Cami on 2017-04-21.
 */

import { Component } from '@angular/core';
import { MdDialogRef } from '@angular/material';

@Component({
  selector: 'ong-dialog',
  templateUrl: './ong.dialog.html',
  styleUrls: ['./ong.dialog.scss']
})
export class OngDialog {

  constructor(public dialogRef: MdDialogRef<OngDialog>) {
  }
}
