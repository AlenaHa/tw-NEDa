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


@NgModule({
  declarations: [
    AppComponent,
    EarthquakeComponent
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule,
    HttpModule,

    MaterialModule.forRoot(),
    FlexLayoutModule,
    NgxDatatableModule
  ],
  providers: [EarthquakeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
