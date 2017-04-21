import {Component, OnInit} from "@angular/core";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  lat: number = 28;
  lng: number = 84;

  public number: number = 1;

  constructor() {

  }

  ngOnInit() {

  }

}
