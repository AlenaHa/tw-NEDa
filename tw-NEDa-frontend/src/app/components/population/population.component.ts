/**
 * Created by Cami on 2017-04-21.
 */
import {
  Component,
} from '@angular/core';
import 'chart.js/dist/Chart.bundle.min.js';

@Component({
  selector: 'filter-demo',
  templateUrl: './population.component.html',
  styleUrls: ['./population.component.scss'],
})

export class PopulationComponent {

  // PolarArea
  public polarAreaChartLabels: string[] = ['0-14', '15-24', '25-39', '40-54', '55-60'];
  public polarAreaChartData: number[] = [250, 300, 400, 200, 30];
  // public polarAreaLegend:boolean = true;
  public polarAreaChartType: string = 'polarArea';


  // Doughnut
  public doughnutChartLabels: string[] = ['Female', 'Male'];
  public doughnutChartData: number[] = [300, 450];
  public doughnutChartType: string = 'doughnut';


  //barChart
  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    // responsive: true
  };
  public barChartLabels: string[] = ['Earthquake 1', 'Earthquake 2', 'Earthquake 3', 'Earthquake 4', 'Earthquake 5', 'Earthquake 6', 'Earthquake 7'];
  public barChartType: string = 'bar';
  // public barChartLegend:boolean = true;
  public barChartData: any[] = [{data: [400, 600, 800, 450, 700, 900, 700], label: 'Number of People'}
  ];

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

}

