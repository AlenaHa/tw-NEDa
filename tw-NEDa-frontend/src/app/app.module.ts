import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { EarthquakeComponent } from './components/earthquakes/earthquake.component';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { MaterialModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { routing } from './app.routing';
import { EarthquakeService } from './services/earthquake.service';
import { EarthquakeDialog } from './components/earthquakes/earthquake.dialog';
import { Md2DatepickerModule } from 'md2-datepicker';
import { Md2DateUtil } from 'md2-datepicker/datepicker/dateUtil';


@NgModule({
  declarations: [
    AppComponent,
    EarthquakeComponent,
    EarthquakeDialog
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule,
    HttpModule,

    MaterialModule.forRoot(),
    FlexLayoutModule,
    NgxDatatableModule,
    Md2DatepickerModule
  ],
  providers: [EarthquakeService, Md2DateUtil],
  bootstrap: [AppComponent],
  entryComponents: [EarthquakeDialog]
})
export class AppModule { }
