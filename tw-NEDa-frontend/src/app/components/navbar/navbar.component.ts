import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit {

  isIn = false;

  /** function that checks if the toggle menu should appear(the menu for the small screen) **/
  toggleState() {
    let bool = this.isIn;
    this.isIn = bool === false ? true : false;
  }

  constructor() {
  }

  ngOnInit() {
  }

}
