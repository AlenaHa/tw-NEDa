import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnInit {

  lat: number = 28;
  lng: number = 84;

  constructor() {
  }

  ngOnInit() {
  }

}
