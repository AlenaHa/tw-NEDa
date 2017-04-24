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
import { HomeComponent } from './components/home/home.component';
import { AgmCoreModule } from 'angular2-google-maps/core';
import { LogoComponent } from './components/logo/logo.component';
import { NepalmapComponent } from './components/nepalmap/nepalmap.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LocationComponent } from './components/location/location.component';
import { Md2DatepickerModule } from 'md2-datepicker';
import { Md2DateUtil } from 'md2-datepicker/datepicker/dateUtil';
import { SuppliesComponent } from './components/supplies/supplies.component';

@NgModule({
  declarations: [
    AppComponent,
    EarthquakeComponent,
    EarthquakeDialog,
    HomeComponent,
    LogoComponent,
    NepalmapComponent,
    NavbarComponent,
    LocationComponent,
    SuppliesComponent
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule,
    HttpModule,

    MaterialModule.forRoot(),
    FlexLayoutModule,
    NgxDatatableModule,
    Md2DatepickerModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBCUrIt3JLF2DlRX6pgDA9ulLZl41uHnTY'
    })
  ],
  providers: [EarthquakeService, Md2DateUtil],
  bootstrap: [AppComponent],
  entryComponents: [EarthquakeDialog]
})
export class AppModule {
}
